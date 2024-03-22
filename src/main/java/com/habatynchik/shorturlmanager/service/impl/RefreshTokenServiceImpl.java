package com.habatynchik.shorturlmanager.service.impl;

import com.habatynchik.shorturlmanager.model.entity.RefreshToken;
import com.habatynchik.shorturlmanager.model.entity.User;
import com.habatynchik.shorturlmanager.repository.RefreshTokenRepository;
import com.habatynchik.shorturlmanager.repository.UserRepository;
import com.habatynchik.shorturlmanager.service.JwtService;
import com.habatynchik.shorturlmanager.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public RefreshToken createRefreshToken(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Error"));

        refreshTokenRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("User already login"));

        RefreshToken refreshToken = RefreshToken.builder()
                .user(user)
                .token(jwtService.generateRefreshToken(user.getEmail()))
                .build();

        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public RefreshToken refreshRefreshToken(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Error"));

        refreshToken.setToken(jwtService.generateRefreshToken(refreshToken.getUser().getEmail()));
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    public Boolean isNotExpire(RefreshToken token) {
        return !jwtService.isTokenExpired(token.getToken());
    }
}
