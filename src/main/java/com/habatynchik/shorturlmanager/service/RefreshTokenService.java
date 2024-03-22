package com.habatynchik.shorturlmanager.service;

import com.habatynchik.shorturlmanager.model.entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(String username);

    RefreshToken refreshRefreshToken(String token);

    Optional<RefreshToken> findByToken(String token);

    Boolean isNotExpire(RefreshToken token);
}
