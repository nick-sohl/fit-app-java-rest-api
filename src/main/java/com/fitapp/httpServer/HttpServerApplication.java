package com.fitapp.httpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.fitapp.httpServer.application.service.MeasurementService;
import com.fitapp.httpServer.infrastructure.adapter.MeasurementRepositoryAdapter;
import com.fitapp.httpServer.presentation.MeasurementHandler;
// Controller
import com.fitapp.httpServer.presentation.StaticFileHandler;
import com.fitapp.httpServer.presentation.controller.measurement.MeasurementController;
import com.sun.net.httpserver.HttpServer;

public class HttpServerApplication {

  private static void runServer() throws IOException {
    // Create Http Server
    HttpServer httpServer = HttpServer.create(new InetSocketAddress("0.0.0.0", 8000), 0);

    // Init Layers
    // Adapter -> Infrastructure Boundary
    MeasurementRepositoryAdapter measurementRepositoryAdapter = new MeasurementRepositoryAdapter();
    // Service -> Business Logic
    MeasurementService measurementService = new MeasurementService(measurementRepositoryAdapter);
    // Controller -> Application Boundary
    MeasurementController measurementController = new MeasurementController(measurementService);
    // Handler -> Transport Boundary
    MeasurementHandler measurementHandler = new MeasurementHandler(measurementController);

    // Define Routes
    // Server home view -> Serve HTML File
    httpServer.createContext("/api", new StaticFileHandler("static/index.html", "text/html"));
    // Serve css file -> is requested from the index.html file in the <head> tag
    httpServer.createContext("/css/styles.css", new StaticFileHandler("static/css/styles.css", "text/css;"));
    // httpServer.createContext("/auth", authHandler);
    httpServer.createContext("/api/measurement", measurementHandler);

    // Start Http Server
    System.out.println("Starting server...");
    httpServer.start();
    System.out.println("Server is running and listening on InetSocketAddress " + httpServer.getAddress());
  }

  public static void main(String[] args) throws IOException {
    HttpServerApplication.runServer();
  }
}
