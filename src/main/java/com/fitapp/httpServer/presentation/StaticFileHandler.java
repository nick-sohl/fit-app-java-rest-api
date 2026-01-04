package com.fitapp.httpServer.presentation;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class StaticFileHandler implements HttpHandler {
  // get resource from classpath
  // add metadata to byte stream
  // respond with byte stream

  private String resource;
  private String type;

  public StaticFileHandler(String resource, String type) {
    this.resource = resource;
    this.type = type;
  }

  private byte[] readFromResource() {
    // try-catch-resource -> Autoclose Stream
    try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resource)) {
      if (inputStream == null) {
        throw new IllegalStateException("Resource not found.");
      }
      return inputStream.readAllBytes();
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  @Override
  public void handle(HttpExchange exchange) throws IOException {

    // Get metadata from the request
    String method = exchange.getRequestMethod();
    Headers headers = exchange.getRequestHeaders();

    // Set Response Headers
    Headers responseHeaders = exchange.getResponseHeaders();
    responseHeaders.add("Access-Control-Allow-Origin", "*");
    responseHeaders.add("Content-Type", type + " charset=utf-8");

    byte[] body = readFromResource();
    exchange.sendResponseHeaders(200, body.length);

    try (OutputStream os = exchange.getResponseBody()) {
      os.write(body);
    }

  }
}
