package com.habatynchik.shorturlmanager.controller;

import com.habatynchik.shorturlmanager.dto.AuthRequestDto;
import com.habatynchik.shorturlmanager.dto.JwtDto;
import com.habatynchik.shorturlmanager.dto.RegistrationDto;
import com.habatynchik.shorturlmanager.model.entity.RefreshToken;
import com.habatynchik.shorturlmanager.service.AuthService;
import com.habatynchik.shorturlmanager.service.JwtService;
import com.habatynchik.shorturlmanager.service.RefreshTokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "API for authorisation users")
public class AuthController {
    private final AuthService authService;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final AuthenticationManager authenticationManager;

    @PostMapping(value = "/register")
    public ResponseEntity<JwtDto> register(@RequestBody RegistrationDto registrationDto) {
        JwtDto jwtDto = authService.registerUser(registrationDto);
        return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public JwtDto login(@RequestBody AuthRequestDto authRequestDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getEmail(), authRequestDto.getPassword()));
        if (authentication.isAuthenticated()) {
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequestDto.getEmail());
            return JwtDto.builder()
                    .accessToken(jwtService.generateToken(authRequestDto.getEmail()))
                    .refreshToken(refreshToken.getToken())
                    .build();
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }

    @PostMapping("/refresh")
    public JwtDto refreshToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return refreshTokenService.findByToken(token.substring(7))
                .filter(refreshTokenService::isNotExpire)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String accessToken = jwtService.generateToken(user.getEmail());
                    String refreshToken = refreshTokenService
                            .refreshRefreshToken(token.substring(7))
                            .getToken();
                    return JwtDto.builder()
                            .accessToken(accessToken)
                            .refreshToken(refreshToken)
                            .build();
                }).orElseThrow(() -> new RuntimeException("Refresh Token is not in DB..!!"));
    }
}
