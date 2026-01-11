package com.fitapp.httpServer.application.mapper;

import com.fitapp.httpServer.application.dto.UserDto;
import com.fitapp.httpServer.domain.entity.User;

public class UserMapper {
  public static UserDto mapToDto(User user) {
    return new UserDto(
        user.getUserId(),
        user.getFname(),
        user.getLname(),
        user.getAge(),
        user.getBodyHeight(),
        user.getBodyWeight());
  }
}
