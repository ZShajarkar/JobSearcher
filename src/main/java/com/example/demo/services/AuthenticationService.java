package com.example.demo.services;

import com.example.demo.dto.JwtResponseDto;
import com.example.demo.dto.LoginRequestDto;

public interface AuthenticationService {
    JwtResponseDto authenticate(LoginRequestDto loginRequestDto);
    Long getIdOutOfBearerToken(String accessToken);
}
