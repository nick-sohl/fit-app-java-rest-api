package com.fitapp.httpServer.presentation.controller.measurement;

import java.util.ArrayList;
import java.util.List;

import com.fitapp.httpServer.application.dto.MeasurementDto;
import com.fitapp.httpServer.application.mapper.MeasurementMapper;
import com.fitapp.httpServer.application.service.MeasurementService;
import com.fitapp.httpServer.domain.entity.Measurement;

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
    return MeasurementMapper.mapToDto(measurement);
  }
}
