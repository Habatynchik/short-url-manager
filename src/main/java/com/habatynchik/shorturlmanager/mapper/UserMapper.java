package com.habatynchik.shorturlmanager.mapper;

import com.habatynchik.shorturlmanager.dto.UserDto;
import com.habatynchik.shorturlmanager.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface UserMapper {

    @Mapping(target = "role", source = "roles")
    UserDto toDto(User entity);

    User toEntity(UserDto dto);
}
