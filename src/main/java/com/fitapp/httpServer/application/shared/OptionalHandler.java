package com.fitapp.httpServer.application.shared;

import java.util.Optional;

public class OptionalHandler {

  public static <T> T getValue(Optional<T> optional) {
    return optional.orElse(null);
  }

}
