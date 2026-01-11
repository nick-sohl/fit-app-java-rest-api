package com.fitapp.httpServer.application.port;

import java.util.List;
import java.util.Optional;

import com.fitapp.httpServer.domain.entity.User;
import com.fitapp.httpServer.application.dto.UserDto;

public interface UserRepositoryInterface {
  Optional<UserDto> findUser(long userId);

  List<UserDto> findAllUsers();

  User createUser(User user);

  UserDto updateUser();

  void deleteUser();

}
