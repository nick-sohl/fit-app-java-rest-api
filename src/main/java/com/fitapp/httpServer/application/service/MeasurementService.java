package com.fitapp.httpServer.application.service;

import java.util.List;
import java.util.Optional;

import com.fitapp.httpServer.application.port.MeasurementRepositoryInterface;
import com.fitapp.httpServer.domain.entity.Measurement;

public class MeasurementService {
  private MeasurementRepositoryInterface measurementRepositoryInterface;

  public MeasurementService(MeasurementRepositoryInterface measurementRepositoryInterface) {
    this.measurementRepositoryInterface = measurementRepositoryInterface;
  }

  public List<Measurement> findAll() {
    return measurementRepositoryInterface.findAllMeasurements();
  }

  public Measurement findMeasurementById(Long mid) {
    Optional<Measurement> optionalMeasurement = measurementRepositoryInterface.findMeasurementById(mid);
    Measurement measurement = getOptional(optionalMeasurement);
    return measurement;

  }

  public Measurement create(Measurement measurement) {
    return measurementRepositoryInterface.addMeasurement(measurement);
  }

  private Measurement getOptional(Optional<Measurement> optionalMeasurement) {
    return optionalMeasurement.orElse(new Measurement());
  }
}
