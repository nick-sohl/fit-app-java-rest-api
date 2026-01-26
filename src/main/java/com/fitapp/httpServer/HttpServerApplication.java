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
import com.fitapp.httpServer.presentation.MeasurementHandler;
// Controller
import com.fitapp.httpServer.presentation.StaticFileHandler;
import com.fitapp.httpServer.presentation.controller.user.UserController;
import com.fitapp.httpServer.presentation.controller.measurement.MeasurementController;

public class HttpServerApplication {

  private static void runServer() throws IOException {
    // Create Http Server
    // NOTE: 0.0.0.0 is a special wildcard.
    // We use it to make the server running in a containers
    // reachable from other containers.
    HttpServer httpServer = HttpServer.create(new InetSocketAddress("0.0.0.0", 8000), 0);
    System.out.println("Http Server created! " + "Address: " + httpServer.getAddress() + "\n");

    // --- Init Layers --- //
    // Adapter -> Infrastructure Boundary
    MeasurementRepositoryAdapter measurementRepositoryAdapter = new MeasurementRepositoryAdapter();

    // Service -> Business Logic
    MeasurementService measurementService = new MeasurementService(measurementRepositoryAdapter);

    // Controller -> Application Boundary
    MeasurementController measurementController = new MeasurementController(measurementService);

    // Handler -> Transport Boundary
    MeasurementHandler measurementHandler = new MeasurementHandler(measurementController);

    // --- Routes --- //
    httpServer.createContext("/api", new StaticFileHandler("static/index.html", "text/html"));
    httpServer.createContext("/css/styles.css", new StaticFileHandler("static/css/styles.css", "text/css;"));
    // Measurement
    httpServer.createContext("/api/measurement", measurementHandler);
    System.out.println("Http Context's created!");

    // Start Http Server
    System.out.println("Starting server...");
    httpServer.start();
    System.out.println("Server is Running!");

  }

  public static void main(String[] args) throws IOException {
    HttpServerApplication.runServer();
  }
}
