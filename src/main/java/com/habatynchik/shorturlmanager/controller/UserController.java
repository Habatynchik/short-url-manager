package com.habatynchik.shorturlmanager.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.habatynchik.shorturlmanager.dto.UserDto;
import com.habatynchik.shorturlmanager.dto.Views;
import com.habatynchik.shorturlmanager.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Tag(name = "User", description = "API for maintaining users")
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    @JsonView(Views.Summary.class)
    public ResponseEntity<UserDto> getMe() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) authentication.getPrincipal();
        String emailFromAccessToken = userDetail.getUsername();

        UserDto userDto = userService.getUserByEmail(emailFromAccessToken);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
