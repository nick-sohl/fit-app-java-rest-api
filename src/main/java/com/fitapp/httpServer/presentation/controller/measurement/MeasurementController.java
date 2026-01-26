package com.fitapp.httpServer.presentation.controller.measurement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fitapp.httpServer.domain.entity.Measurement;
import com.fitapp.httpServer.presentation.MeasurementHandler;
import com.sun.net.httpserver.HttpExchange;

import com.fitapp.httpServer.presentation.utility.JsonRequestReader;
import com.fitapp.httpServer.application.mapper.MeasurementMapper;
import com.fitapp.httpServer.application.service.MeasurementService;

// FIX: Work in progress.
public class MeasurementController {
  private MeasurementService measurementService;

  public MeasurementController(MeasurementService measurementService) {
    this.measurementService = measurementService;
  }

  public List<MeasurementDto> findAll() {
    List<Measurement> listOfMeasurements = measurementService.findAll();
    List<MeasurementDto> listOfDtos = new ArrayList<>();

    for (Measurement measurement : listOfMeasurements) {
      MeasurementDto dto = MeasurementMapper.mapToDto(measurement);
      listOfDtos.add(dto);
    }

    return listOfDtos;
  }

  public MeasurementDto findMeasurementById(Long mid) {
    Measurement measurement = measurementService.findMeasurementById(mid);
  }

  @Override
  protected void doPost(HttpExchange exchange) {
    // MeasurementRequest dto = JsonRequestReader.read(exchange.getRequestBody(),
    // MeasurementRequest.class);
    Measurement measurement = measurementService
        .create(JsonRequestReader.read(exchange.getRequestBody(), Measurement.class));

    System.out.println(measurement);
    try {
      byte[] bytes = exchange.getRequestBody().readAllBytes();

      exchange.sendResponseHeaders(200, bytes.length);
      exchange.getResponseBody().write(bytes);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
