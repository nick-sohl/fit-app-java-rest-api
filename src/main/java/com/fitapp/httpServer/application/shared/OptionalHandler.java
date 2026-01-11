package com.fitapp.httpServer.application.shared;

import java.util.Optional;

public class OptionalHandler {

  public static <T> boolean validateOptional(Optional<T> optional) {
    if (!optional.isPresent()) {
      return false;
    }
    return true;
  }

}
