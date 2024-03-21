package com.habatynchik.shorturlmanager.repository;

import com.habatynchik.shorturlmanager.model.entity.URLInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface URLInfoRepository extends JpaRepository<URLInfo, Long> {
}
