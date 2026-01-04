package com.fitapp.httpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.Connection;

import com.fitapp.httpServer.application.service.UserService;
import com.fitapp.httpServer.infrastructure.adapter.UserRepositoryAdapter;
import com.fitapp.httpServer.infrastructure.persistence.DatabaseConfig;
import com.fitapp.httpServer.infrastructure.persistence.UserRepository;
import com.fitapp.httpServer.presentation.api.UserController;
import com.fitapp.httpServer.presentation.api.WelcomeController;
import com.sun.net.httpserver.HttpServer;

public class HttpServerApplication {

  private static WelcomeController welcomeController;
  private static UserController userController;

  private static void runServer() throws IOException {
    // Create Http Server
    // "localhost" = 127.0.0.1 /
    HttpServer httpServer = HttpServer.create(new InetSocketAddress("0.0.0.0", 8000), 0);
    System.out.println("Http Server created! " + "Address: " + httpServer.getAddress() + "\n");

    // Routes
    httpServer.createContext("/api", welcomeController);
    httpServer.createContext("/api/user", userController);
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
    // Init Controller which is our HttpHandler to handle Http Requests
    // Uses the Service to get the computed data
    HttpServerApplication.welcomeController = new WelcomeController();
    HttpServerApplication.userController = new UserController(userService);
    // Init App, provide UserController and start the Server
    HttpServerApplication.runServer();
    System.out.println("Server is Running!");
  }
}
