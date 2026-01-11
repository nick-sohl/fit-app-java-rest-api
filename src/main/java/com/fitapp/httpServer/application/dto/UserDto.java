package com.fitapp.httpServer.application.dto;

public record UserDto(Long userId, String fname, String lname, int age, int bodyHeight, int bodyWeight) {
}
