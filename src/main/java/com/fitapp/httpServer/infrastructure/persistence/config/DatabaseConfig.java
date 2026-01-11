package com.fitapp.httpServer.infrastructure.persistence.config;

import com.fitapp.httpServer.infrastructure.persistence.jdbc.JdbcConnector;

public class DatabaseConfig {
  public static final JdbcConnector JDBC_CONNECTOR = new JdbcConnector.Builder()
      .protocol("jdbc:postgresql")
      .host("db")
      .port(5432)
      .dbName("fit-app")
      .dbUser("postgres")
      .dbPassword("postgres")
      .build();
}
