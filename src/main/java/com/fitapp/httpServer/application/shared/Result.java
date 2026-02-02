package com.fitapp.httpServer.application.shared;

import com.fitapp.httpServer.domain.enumeration.ErrorCode;

public record Result<T>(
    boolean success,
    T data,
    ErrorCode errorCode,
    String errorMessage) {

  public static <T> Result<T> success(T data) {
    return new Result<>(true, data, null, null);
  }

  public static <T> Result<T> failure(ErrorCode errorCode, String errorMessage) {
    return new Result<>(false, null, errorCode, errorMessage);
  }

  public boolean isFailure() {
    return !success;
  }

}
