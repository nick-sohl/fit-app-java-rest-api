package com.fitapp.httpServer.application.dto;

import java.time.Instant;

public record MeasurementDto(Long mid, Long cid, Instant measuredAt) {
}
