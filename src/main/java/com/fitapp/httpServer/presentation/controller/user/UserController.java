package com.fitapp.httpServer.presentation.controller.user;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fitapp.httpServer.application.service.UserService;
import com.fitapp.httpServer.presentation.RestHandler;
import com.sun.net.httpserver.HttpExchange;

public class UserController extends RestHandler {

  UserService userService;
  ObjectMapper mapper = new ObjectMapper();

  String basePath = "/api/user";
  String remainingPath = basePath.substring(basePath.length());

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @Override
  protected void doGet(HttpExchange exchange) {
    try {
      byte[] bytes = mapper.writeValueAsBytes(userService.findAllUsers());
      exchange.sendResponseHeaders(200, bytes.length);
      exchange.getResponseBody().write(bytes);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void doPost(HttpExchange exchange) {
    try {
      byte[] body = exchange.getRequestBody().readAllBytes();
      // String json = mapper.readValue(body, CreateUserCommand.class);

      // Deserialize → validate → execute
      exchange.sendResponseHeaders(200, body.length);
      exchange.getResponseBody().write(body);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
