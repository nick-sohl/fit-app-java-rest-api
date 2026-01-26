package com.fitapp.httpServer.presentation;

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
    }

    // TODO: Work on path handling
  }

  @Override
  protected void doPost(HttpExchange exchange) {
  }

  @Override
  protected void doDelete(HttpExchange exchange) {
  }
}
