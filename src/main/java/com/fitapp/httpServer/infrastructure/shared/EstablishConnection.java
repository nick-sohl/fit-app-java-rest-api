package com.fitapp.httpServer.infrastructure.shared;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.fitapp.httpServer.infrastructure.persistence.config.DatabaseConfig;

public class EstablishConnection {

  public final static Connection establishConnection() {
    try {
      Connection connection = DriverManager.getConnection(
          DatabaseConfig.JDBC_CONNECTOR.DB_URL,
          DatabaseConfig.JDBC_CONNECTOR.DB_USER,
          DatabaseConfig.JDBC_CONNECTOR.DB_PASSWORD);

      if (connection.isValid(0)) {
        System.out.println("Connection established to database.");
        System.out.println(connection.getMetaData());
      }

      return connection;
    } catch (SQLException e) {
      throw new RuntimeException("Could not establish a connection to the Database");
    }
  }
}
