package com.fitapp.httpServer.presentation;

import java.io.IOException;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

// INFO: Template Method Pattern
// Set default headers, handle error, exchange

abstract class BaseHttpHandler implements HttpHandler {

  // DEFINE ALLOWED ORIGIN FOR CORS
  private final static String ORIGIN = "http://localhost:4321"; // Astro Frontend

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    try {
      setCommonHeaders(exchange);
      handleRequest(exchange);
    } catch (Exception e) {
      handleError(exchange, e);
    } finally {
      exchange.close();
    }
  }

  // SET COMMON HEADERS
  private void setCommonHeaders(HttpExchange exchange) {
    Headers headers = exchange.getResponseHeaders();
    headers.add("Content-Type", "application/json");
    headers.add("Access-Control-Allow-Origin", ORIGIN);
    headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, OPTION");
    headers.add("Access-Control-Allow-Headers", "Content-Type");
    headers.add("Access-Control-Max-Age", "86400");
  }

  // DEFINE METHOD HANDLE REQUEST
  protected abstract void handleRequest(HttpExchange exchange) throws IOException;

  // HANDLE ERRORS
  private void handleError(HttpExchange exchange, Exception e) throws IOException {
    e.printStackTrace();
    exchange.sendResponseHeaders(500, -1);
  }
}
