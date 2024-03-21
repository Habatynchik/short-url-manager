package com.habatynchik.shorturlmanager.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomUrlDto {
    @URL
    private String longURL;
    @Size(min = 2, max = 64)
    @Pattern(regexp = "[^`?~,<>;':\"\\/\\\\ \\[\\]^\\{\\}()+!*@&$%|\\n]+")
    private String shortCode;
    @Future
    private LocalDate expiredDate;
}
