package com.habatynchik.shorturlmanager.dto;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @JsonView(Views.Details.class)
    private Long id;

    @NotBlank
    @JsonView(Views.Summary.class)
    private String displayName;

    @Email
    @JsonView(Views.Summary.class)
    private String email;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{8,}$")
    @JsonView(Views.FullDetails.class)
    private String password;

    @NotNull
    @JsonView(Views.Details.class)
    private LocalDate registrationDate;

    @NotNull
    @JsonView(Views.Summary.class)
    private RoleDto role;
}
