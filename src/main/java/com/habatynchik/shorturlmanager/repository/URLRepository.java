package com.habatynchik.shorturlmanager.repository;

import com.habatynchik.shorturlmanager.model.entity.URL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface URLRepository extends JpaRepository<URL, Long> {
    Optional<URL> findByShortCode(String shortCode);
}
