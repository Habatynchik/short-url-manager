package com.habatynchik.shorturlmanager.service;

import com.habatynchik.shorturlmanager.dto.JwtDto;
import com.habatynchik.shorturlmanager.dto.RegistrationDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {

    JwtDto registerUser(RegistrationDto registrationDTO);
}
