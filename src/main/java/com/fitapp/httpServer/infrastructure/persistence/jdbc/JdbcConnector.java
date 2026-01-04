package com.fitapp.httpServer.infrastructure.persistence.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnector {
  private final String PROTOCOL;
  private final String HOST;
  private final int PORT;
  private final String DB_NAME;

  public final String DB_USER;
  public final String DB_PASSWORD;

  public final String DB_URL;

  public JdbcConnector(Builder builder) {
    this.PROTOCOL = builder.protocol;
    this.HOST = builder.host;
    this.PORT = builder.port;
    this.DB_NAME = builder.dbName;
    this.DB_USER = builder.dbUser;
    this.DB_PASSWORD = builder.dbPassword;
    // build the URL from the arguments.
    // For example: jdbc:postgresql://db:5432/fit-app
    this.DB_URL = PROTOCOL + "://" + HOST + ":" + PORT + "/" + DB_NAME;
  }

  public static class Builder {
    private String protocol;
    private String host;
    private int port;
    private String dbName;
    private String dbUser;
    private String dbPassword;

    public Builder protocol(String protocol) {
      this.protocol = protocol;
      return this;
    }

    public Builder host(String host) {
      this.host = host;
      return this;
    }

    public Builder port(int port) {
      this.port = port;
      return this;
    }

    public Builder dbName(String dbName) {
      this.dbName = dbName;
      return this;
    }

    public Builder dbUser(String dbUser) {
      this.dbUser = dbUser;
      return this;
    }

    public Builder dbPassword(String dbPassword) {
      this.dbPassword = dbPassword;
      return this;
    }

    public JdbcConnector build() {
      return new JdbcConnector(this);
    }

  }

}
