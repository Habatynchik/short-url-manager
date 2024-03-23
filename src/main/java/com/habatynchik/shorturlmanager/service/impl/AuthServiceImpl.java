package com.habatynchik.shorturlmanager.service.impl;

import com.habatynchik.shorturlmanager.dto.JwtDto;
import com.habatynchik.shorturlmanager.dto.RegistrationDto;
import com.habatynchik.shorturlmanager.dto.UserDto;
import com.habatynchik.shorturlmanager.exception.EmailAlreadyExistException;
import com.habatynchik.shorturlmanager.exception.PasswordsNotMatchException;
import com.habatynchik.shorturlmanager.mapper.RoleMapper;
import com.habatynchik.shorturlmanager.model.entity.RefreshToken;
import com.habatynchik.shorturlmanager.model.entity.Role;
import com.habatynchik.shorturlmanager.model.entity.User;
import com.habatynchik.shorturlmanager.model.enums.RolesEnum;
import com.habatynchik.shorturlmanager.repository.RolesRepository;
import com.habatynchik.shorturlmanager.service.AuthService;
import com.habatynchik.shorturlmanager.service.JwtService;
import com.habatynchik.shorturlmanager.service.RefreshTokenService;
import com.habatynchik.shorturlmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final RoleMapper roleMapper;
    private final JwtService jwtService;
    private final UserService userService;
    private final RolesRepository rolesRepository;
    private final RefreshTokenService refreshTokenService;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public JwtDto registerUser(RegistrationDto registrationDTO) {
        if (userService.existsByEmail(registrationDTO.getEmail())) {
            throw new EmailAlreadyExistException(registrationDTO.getEmail());
        }

        if (!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())) {
            throw new PasswordsNotMatchException();
        }

        Role role = rolesRepository.findByName(RolesEnum.CLIENT)
                .orElseThrow(RuntimeException::new);

        UserDto userDto = UserDto.builder()
                .email(registrationDTO.getEmail())
                .password(encoder.encode(registrationDTO.getPassword()))
                .displayName(UUID.randomUUID().toString().substring(0, 8))
                .registrationDate(LocalDate.now())
                .role(roleMapper.toDto(role))
                .build();

        User user = userService.saveUser(userDto);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getEmail());
        String accessToken = jwtService.generateToken(user.getEmail());

        return JwtDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getToken())
                .build();
    }
}
