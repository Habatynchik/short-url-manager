package com.habatynchik.shorturlmanager.service.impl;

import com.habatynchik.shorturlmanager.dto.UserDto;
import com.habatynchik.shorturlmanager.exception.UserNotFoundException;
import com.habatynchik.shorturlmanager.mapper.UserMapper;
import com.habatynchik.shorturlmanager.model.entity.User;
import com.habatynchik.shorturlmanager.repository.UserRepository;
import com.habatynchik.shorturlmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User saveUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        return userRepository.save(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
        return userMapper.toDto(user);
    }

    @Override
    public List<UserDto> getAllUser() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }
}
