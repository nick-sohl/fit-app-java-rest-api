package com.fitapp.httpServer.presentation.utility;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonRequestReader {
  private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  public static <T> T read(InputStream is, Class<T> type) {
    try {
      return OBJECT_MAPPER.readValue(is, type);
    } catch (IOException e) {
      throw new RuntimeException("Invalid JSON", e);
    }
  }
}
