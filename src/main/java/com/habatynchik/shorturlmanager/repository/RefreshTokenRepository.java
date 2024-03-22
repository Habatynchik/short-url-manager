package com.habatynchik.shorturlmanager.repository;

import com.habatynchik.shorturlmanager.model.entity.RefreshToken;
import com.habatynchik.shorturlmanager.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    Optional<RefreshToken> findByUser(User user);

    Boolean existsByUser(User user);
}
