package com.fitapp.httpServer.application.port;

import java.util.List;
import java.util.Optional;

import com.fitapp.httpServer.domain.entity.Measurement;

public interface MeasurementRepositoryInterface {
  List<Measurement> findAllMeasurements();

  Optional<Measurement> findMeasurment(int measurementId);

  Measurement addMeasurement(Measurement measurement);

  Measurement updateMeasurement(Measurement measurement);

  void deleteMeasurement(int measurementId);
}
