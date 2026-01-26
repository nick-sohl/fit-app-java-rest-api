package com.fitapp.httpServer.domain.entity;

import java.time.Instant;

public class Measurement {
  private Long mid;
  private Long clientId;
  private Instant measuredAt;

  public Long getMeasurementId() {
    return mid;
  }

  public void setMeasurementId(Long mid) {
    this.mid = mid;
  }

  public Long getClientId() {
    return clientId;
  }

  public void setClientId(Long clientId) {
    this.clientId = clientId;
  }

  public Instant getMeasuredAt() {
    return measuredAt;
  }

  public void setMeasuredAt(Instant measuredAt) {
    this.measuredAt = measuredAt;
  }
}
