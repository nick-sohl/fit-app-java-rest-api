package com.fitapp.httpServer.application.mapper;

import com.fitapp.httpServer.application.dto.MeasurementDto;
import com.fitapp.httpServer.domain.entity.Measurement;

// FIX : Work in progess.
public class MeasurementMapper {

  public static final MeasurementDto mapToDto(Measurement measurement) {
    return new MeasurementDto(
        measurement.getMeasurementId(),
        measurement.getClient(),
        measurement.getAge(),
        measurement.getBodyHeight(),
        measurement.getBodyWeight());
  }
}
