package com.fitapp.httpServer.application.usecase.user;

import java.util.Optional;

import com.fitapp.httpServer.application.port.UserRepositoryInterface;
import com.fitapp.httpServer.application.dto.UserDto;

public class FindUser {
  UserRepositoryInterface userRepositoryInterface;

  public FindUser(UserRepositoryInterface userRepositoryInterface) {
    this.userRepositoryInterface = userRepositoryInterface;
  }

  public Optional<UserDto> findUser(int userId) {
    Optional<UserDto> user = userRepositoryInterface.findUser(userId);
    return user;
  }
}
