package com.fitapp.httpServer.presentation.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fitapp.httpServer.application.service.UserService;
import com.fitapp.httpServer.domain.entity.User;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class UserController implements HttpHandler {
  UserService userService;
  ObjectMapper mapper = new ObjectMapper();

  public UserController(UserService userService) {
    this.userService = userService;
  }

  private List<User> findAllUsers() {
    return userService.findAllUsers();
  }

  private String convertToJsonString(User user) throws JacksonException {
    // Java Object -> JSON
    String json = mapper.writeValueAsString(user);
    return json;
  }

  private User convertToObject(String jsonString) throws JsonMappingException, JsonProcessingException {
    // JSON -> Java Object
    return mapper.readValue(jsonString, User.class);
  }

  @Override
  public void handle(HttpExchange exchange) throws IOException {

    // Gathering Infos
    String method = exchange.getRequestMethod();
    Headers requestHeaders = exchange.getRequestHeaders();
    InputStream body = exchange.getRequestBody();

    Headers responseHeaders = exchange.getResponseHeaders();
    responseHeaders.add("Server", "Nicks HTTP Server");
    responseHeaders.add("Content-Type", "application/json; charset=utf-8");
    responseHeaders.add("Access-Control-Allow-Origin", "*");

    if (!"GET".equals(method)) {
      exchange.sendResponseHeaders(405, -1);
      return;
    }

    try {
      List<User> users = findAllUsers();
      String json = mapper.writeValueAsString(users);
      byte[] bytes = json.getBytes(StandardCharsets.UTF_8);

      exchange.sendResponseHeaders(200, bytes.length);
      try (OutputStream os = exchange.getResponseBody()) {
        os.write(bytes);
      }
    } catch (Exception e) {
      e.printStackTrace();
      exchange.sendResponseHeaders(500, -1);
    }
  }
}
