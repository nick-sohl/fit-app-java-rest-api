package com.fitapp.httpServer.presentation;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.fitapp.httpServer.application.dto.MeasurementDto;
import com.fitapp.httpServer.presentation.controller.measurement.MeasurementController;
import com.sun.net.httpserver.HttpExchange;

// INFO: Parse path, deserialize JSON, call controller method, map result to HTTP response

public class MeasurementHandler extends RestHandler {

  MeasurementController measurementController;

  // TODO: Think about Controller Interface
  public MeasurementHandler(MeasurementController measurementController) {
    this.measurementController = measurementController;
  }

  private static final String BASE_PATH = "/api/measurement";

  // parse path
  // deserialize Data -> convert to object
  // call controller method
  // map resultto http response

  @Override
  protected void doGet(HttpExchange exchange) {
    String path = exchange.getRequestURI().getPath();
    String remaining = path.substring(BASE_PATH.length());

    if (remaining.isEmpty() || remaining.equals("/")) {
      measurementController.findAll();
      return;
    }

    String[] parts = remaining.split("/");
    if (parts.length > 1) {
      try {
        exchange.sendResponseHeaders(418, -1);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    // TODO: Work on path handling
    MeasurementDto measurementDto = measurementController.findMeasurementById(Long.parseLong(parts[0]));
    byte[] bytes = serialize(measurementDto);
    try {
      exchange.sendResponseHeaders(200, bytes.length);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  static byte[] serialize(final Object obj) {
    ByteArrayOutputStream bos = new ByteArrayOutputStream();

    try (ObjectOutputStream out = new ObjectOutputStream(bos)) {
      out.writeObject(obj);
      out.flush();
      return bos.toByteArray();
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }

  // protected void doPost(HttpExchange exchange) {
  // // MeasurementRequest dto = JsonRequestReader.read(exchange.getRequestBody(),
  // // MeasurementRequest.class);
  // Measurement measurement = measurementService
  // .create(JsonRequestReader.read(exchange.getRequestBody(),
  // Measurement.class));
  //
  // System.out.println(measurement);
  // try {
  // byte[] bytes = exchange.getRequestBody().readAllBytes();
  //
  // exchange.sendResponseHeaders(200, bytes.length);
  // exchange.getResponseBody().write(bytes);
  //
  // } catch (IOException e) {
  // e.printStackTrace();
  // }
  // }

  @Override
  protected void doDelete(HttpExchange exchange) {
  }
}
