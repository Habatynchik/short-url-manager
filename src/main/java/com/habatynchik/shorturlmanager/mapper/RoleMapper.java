package com.habatynchik.shorturlmanager.mapper;

import com.habatynchik.shorturlmanager.dto.RoleDto;
import com.habatynchik.shorturlmanager.model.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto toDto(Role entity);

    Role toEntity(RoleDto dto);
}
