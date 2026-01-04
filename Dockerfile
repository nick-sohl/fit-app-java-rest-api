# Summary
# 1. Start with a full JDK environment.
# 2. Fetch all libraries defined in pom.xml (cached for speed).
# 3. Add your source code and compile the JAR.
# 4. Discard the build environment entirely.
# 5. Create a fresh, tiny image containing only the JRE and your single JAR file.

# Multi Stage Build
# Each FROM starts a new temporary stage

# Goal: Download all Java dependencies (libraries) before the source code is even added.
FROM eclipse-temurin:21-jdk-jammy AS deps
WORKDIR /build

COPY --chmod=0755 mvnw mvnw
COPY .mvn/ .mvn/
COPY pom.xml pom.xml

# --mount=type=cache -> keep a persistent cache of the Maven repository (.m2)
RUN --mount=type=cache,target=/root/.m2 \
  ./mvnw dependency:go-offline -DskipTests

# Goal: Compile the source code and package it into a .jar file.
FROM deps AS build
WORKDIR /build

COPY src src/

RUN --mount=type=cache,target=/root/.m2 \
  ./mvnw package -DskipTests && \
  mv target/fitapp-1.0-SNAPSHOT.jar target/app.jar

# The Swap: The base image changed from jdk (Java Development Kit) to jre (Java Runtime Environment).
# The JRE is much smaller because it lacks compilers and debugging tools—things you don't want in production for security and size reasons.
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# "reaches back" into the previous stage to grab only the final JAR file.
COPY --from=build /build/target/app.jar app.jar

EXPOSE 8000
ENTRYPOINT ["java", \
  "-Dcom.sun.management.jmxremote", \
  "-Dcom.sun.management.jmxremote.port=9010", \
  "-Dcom.sun.management.jmxremote.rmi.port=9010", \
  "-Dcom.sun.management.jmxremote.local.only=false", \
  "-Dcom.sun.management.jmxremote.authenticate=false", \
  "-Dcom.sun.management.jmxremote.ssl=false", \
  "-Djava.rmi.server.hostname=localhost", \
  "-jar", "app.jar"]
