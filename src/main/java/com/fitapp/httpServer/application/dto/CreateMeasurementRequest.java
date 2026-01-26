package com.fitapp.httpServer.application.dto;

import java.time.Instant;

public record CreateMeasurementRequest(Long clientId, Instant measuredAt) {
}
