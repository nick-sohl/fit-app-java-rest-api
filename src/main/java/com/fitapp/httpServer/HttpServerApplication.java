package com.fitapp.httpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.fitapp.httpServer.application.service.UserService;
import com.fitapp.httpServer.infrastructure.adapter.UserRepositoryAdapter;
import com.fitapp.httpServer.infrastructure.persistence.UserRepository;
import com.fitapp.httpServer.presentation.StaticFileHandler;
import com.fitapp.httpServer.presentation.api.UserController;
import com.sun.net.httpserver.HttpServer;

public class HttpServerApplication {

  private static UserController userController;

  private static void runServer() throws IOException {
    // Create Http Server
    // NOTE: 0.0.0.0 is a special wildcard.
    // We use it to make the server running in a containers
    // reachable from other containers.
    HttpServer httpServer = HttpServer.create(new InetSocketAddress("0.0.0.0", 8000), 0);
    System.out.println("Http Server created! " + "Address: " + httpServer.getAddress() + "\n");

    // Routes
    httpServer.createContext("/api", new StaticFileHandler("static/index.html", "text/html"));
    httpServer.createContext("/api/user", userController);
    httpServer.createContext("/css/styles.css", new StaticFileHandler("static/css/styles.css", "text/css;"));

    System.out.println("Http Context's created!");
    // Start Http Server
    httpServer.start();
    System.out.println("Starting server...");
  }

  public static void main(String[] args) throws IOException {
    // Init Repo
    UserRepository userRepository = new UserRepository();
    // Init Adapter which is implementing Interface and using the methods of the
    // Repo to retrieve data from the DB
    UserRepositoryAdapter userRepositoryAdapter = new UserRepositoryAdapter(userRepository);
    // Init Service which is using the Adapter to retrieve data to work with the
    // data
    UserService userService = new UserService(userRepositoryAdapter);
    // Uses the Service to get the computed data
    HttpServerApplication.userController = new UserController(userService);
    HttpServerApplication.runServer();
    System.out.println("Server is Running!");
  }
}
