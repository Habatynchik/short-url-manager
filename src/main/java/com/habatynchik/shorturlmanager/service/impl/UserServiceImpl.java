package com.habatynchik.shorturlmanager.service.impl;

import com.habatynchik.shorturlmanager.dto.UserDto;
import com.habatynchik.shorturlmanager.exception.UserNotFoundException;
import com.habatynchik.shorturlmanager.model.entity.User;
import com.habatynchik.shorturlmanager.repository.UserRepository;
import com.habatynchik.shorturlmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User saveUser(UserDto userDto) {
        User user = User.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .displayName(userDto.getDisplayName())
                .registrationDate(userDto.getRegistrationDate())
                .roles(userDto.getRole())
                .build();
        return userRepository.save(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));

        return UserDto.builder()
                .email(user.getEmail())
                .role(user.getRoles())
                .displayName(user.getDisplayName())
                .build();
    }

    @Override
    public List<UserDto> getAllUser() {
        return null;
    }


}
