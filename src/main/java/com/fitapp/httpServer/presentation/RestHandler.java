package com.fitapp.httpServer.presentation;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;

// INFO: Handle methods, handle options request, dispatch GET, POST, DELETE

public abstract class RestHandler extends BaseHttpHandler {

  @Override
  protected final void handleRequest(HttpExchange exchange) throws IOException {
    String method = exchange.getRequestMethod();

    // INFO: The client requests permitted communication options for the given URL
    // When you make a "non-simple" cross-origin request, the browser first sends an
    // OPTIONS request to the server. This preflight request checks if the actual
    // request is allowed based on origin, method, and headers.
    if ("OPTIONS".equalsIgnoreCase(method)) {
      exchange.sendResponseHeaders(204, -1);
      return;
    }

    switch (method) {
      case "GET" -> doGet(exchange);
      case "POST" -> doPost(exchange);
      case "DELETE" -> doDelete(exchange);
      default -> exchange.sendResponseHeaders(405, -1);
    }
  }

  protected void doGet(HttpExchange exchange) {
    try {
      exchange.sendResponseHeaders(405, -1);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  protected void doPost(HttpExchange exchange) {
    try {
      exchange.sendResponseHeaders(405, -1);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  protected void doDelete(HttpExchange exchange) {
    try {
      exchange.sendResponseHeaders(405, -1);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
