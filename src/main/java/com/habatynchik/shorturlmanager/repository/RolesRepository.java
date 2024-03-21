package com.habatynchik.shorturlmanager.repository;

import com.habatynchik.shorturlmanager.model.entity.Role;
import com.habatynchik.shorturlmanager.model.enums.RolesEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RolesEnum role);
}
