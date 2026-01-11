package com.fitapp.httpServer.infrastructure.adapter;

import java.util.List;
import java.util.Optional;

import com.fitapp.httpServer.application.port.MeasurementRepositoryInterface;
import com.fitapp.httpServer.domain.entity.Measurement;
import com.fitapp.httpServer.infrastructure.persistence.repository.MeasurementRepository;

public class MeasurementRepositoryAdapter implements MeasurementRepositoryInterface {
  private MeasurementRepository measurementRepository;

  @Override
  public List<Measurement> findAllMeasurements() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findAllMeasurements'");
  }

  @Override
  public Optional<Measurement> findMeasurment(int measurementId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findMeasurment'");
  }

  @Override
  public Measurement addMeasurement(Measurement measurement) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'addMeasurement'");
  }

  @Override
  public Measurement updateMeasurement(Measurement measurement) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateMeasurement'");
  }

  @Override
  public void deleteMeasurement(int measurementId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteMeasurement'");
  }

}
