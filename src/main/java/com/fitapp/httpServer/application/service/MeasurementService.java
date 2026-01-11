package com.fitapp.httpServer.application.service;

import com.fitapp.httpServer.application.port.MeasurementRepositoryInterface;

public class MeasurementService {
  private MeasurementRepositoryInterface measurementRepositoryInterface;

  public MeasurementService(MeasurementRepositoryInterface measurementRepositoryInterface) {
    this.measurementRepositoryInterface = measurementRepositoryInterface;
  }
}
