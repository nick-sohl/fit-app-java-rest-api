package com.fitapp.httpServer;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

import com.fitapp.httpServer.infrastructure.adapter.MeasurementRepositoryAdapter;
// Adapter
import com.fitapp.httpServer.infrastructure.adapter.UserRepositoryAdapter;

// DAO
import com.fitapp.httpServer.infrastructure.persistence.repository.UserRepository;
import com.fitapp.httpServer.application.service.MeasurementService;
// Service
import com.fitapp.httpServer.application.service.UserService;

// Controller
import com.fitapp.httpServer.presentation.StaticFileHandler;
import com.fitapp.httpServer.presentation.controller.UserController;
import com.fitapp.httpServer.presentation.controller.MeasurementController;

public class HttpServerApplication {

  private static UserController userController;
  private static MeasurementController measurementController;

  private static void runServer() throws IOException {
    // Create Http Server
    // NOTE: 0.0.0.0 is a special wildcard.
    // We use it to make the server running in a containers
    // reachable from other containers.
    HttpServer httpServer = HttpServer.create(new InetSocketAddress("0.0.0.0", 8000), 0);
    System.out.println("Http Server created! " + "Address: " + httpServer.getAddress() + "\n");

    // Routes
    httpServer.createContext("/api", new StaticFileHandler("static/index.html", "text/html"));
    httpServer.createContext("/css/styles.css", new StaticFileHandler("static/css/styles.css", "text/css;"));
    // User
    httpServer.createContext("/api/user", userController);
    // Measurement
    httpServer.createContext("/api/measurement", measurementController);
    System.out.println("Http Context's created!");

    // Start Http Server
    System.out.println("Starting server...");
    httpServer.start();
    System.out.println("Server is Running!");
  }

  public static void main(String[] args) throws IOException {
    // Repository
    UserRepository userRepository = new UserRepository();

    // Adapter
    UserRepositoryAdapter userRepositoryAdapter = new UserRepositoryAdapter(userRepository);
    MeasurementRepositoryAdapter measurementRepositoryAdapter = new MeasurementRepositoryAdapter();

    // Service
    UserService userService = new UserService(userRepositoryAdapter);
    MeasurementService measurementService = new MeasurementService(measurementRepositoryAdapter);

    // Controller
    HttpServerApplication.userController = new UserController(userService);
    HttpServerApplication.measurementController = new MeasurementController(measurementService);
    HttpServerApplication.runServer();
  }
}
