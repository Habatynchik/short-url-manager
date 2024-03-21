package com.habatynchik.shorturlmanager.model.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.habatynchik.shorturlmanager.dto.Views;
import com.habatynchik.shorturlmanager.model.enums.RolesEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role {
    @Id
    @JsonView(Views.Details.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @JsonView(Views.Summary.class)
    private RolesEnum name;
}
