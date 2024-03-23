package com.habatynchik.shorturlmanager.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.habatynchik.shorturlmanager.model.enums.RolesEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    @JsonView(Views.Details.class)
    private Long id;

    @JsonView(Views.Summary.class)
    private RolesEnum name;
}
