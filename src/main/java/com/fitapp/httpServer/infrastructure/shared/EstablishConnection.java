package com.fitapp.httpServer.infrastructure.shared;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.fitapp.httpServer.infrastructure.persistence.config.DatabaseConfig;

public class EstablishConnection {
  public static Connection connection;

  public final static void establishConnection() {
    try {
      EstablishConnection.connection = DriverManager.getConnection(
          DatabaseConfig.JDBC_CONNECTOR.DB_URL,
          DatabaseConfig.JDBC_CONNECTOR.DB_USER,
          DatabaseConfig.JDBC_CONNECTOR.DB_PASSWORD);
      System.out.println("Connection to Database Established \n Meta-Data: " + connection.getMetaData());
    } catch (SQLException e) {
      throw new RuntimeException("Could not establish a connection to the Database");
    }
  }
}
