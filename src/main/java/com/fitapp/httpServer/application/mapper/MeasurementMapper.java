package com.fitapp.httpServer.application.mapper;

import com.fitapp.httpServer.application.dto.MeasurementDto;
import com.fitapp.httpServer.domain.entity.Measurement;

public class MeasurementMapper {

  public static final MeasurementDto mapToDto(Measurement measurement) {
    return new MeasurementDto(
        measurement.getMeasurementId(), measurement.getClientId(), measurement.getMeasuredAt());
  }
}
