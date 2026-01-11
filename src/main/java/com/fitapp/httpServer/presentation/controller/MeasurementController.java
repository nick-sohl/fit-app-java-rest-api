package com.fitapp.httpServer.presentation.controller;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fitapp.httpServer.domain.entity.Measurement;
import com.fitapp.httpServer.presentation.RestHandler;
import com.sun.net.httpserver.HttpExchange;

import com.fitapp.httpServer.presentation.utility.JsonRequestReader;
import com.fitapp.httpServer.application.dto.MeasurementRequest;

public class MeasurementController extends RestHandler {

  @Override
  protected void doGet(HttpExchange exchange) {
  }

  @Override
  protected void doPost(HttpExchange exchange) {
    MeasurementRequest dto = JsonRequestReader.read(exchange.getRequestBody(), MeasurementRequest.class);
    try {
      byte[] bytes = exchange.getRequestBody().readAllBytes();

      exchange.sendResponseHeaders(200, bytes.length);
      exchange.getResponseBody().write(bytes);

      ObjectMapper objectMapper = new ObjectMapper();
      Measurement measurement = objectMapper.readValue(bytes, Measurement.class);
      System.out.println(measurement.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
