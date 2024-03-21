package com.habatynchik.shorturlmanager.service;

import com.habatynchik.shorturlmanager.dto.UserDto;
import com.habatynchik.shorturlmanager.model.entity.User;

import java.util.List;

public interface UserService {
    boolean existsByEmail(String email);

    User saveUser(UserDto userDto);

    UserDto getUserByEmail(String email);

    List<UserDto> getAllUser();
}
