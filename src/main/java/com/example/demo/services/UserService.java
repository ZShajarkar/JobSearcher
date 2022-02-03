package com.example.demo.services;

import com.example.demo.dto.JwtResponseDto;
import com.example.demo.dto.LoginRequestDto;
import com.example.demo.dto.SignUpUserRequestDto;
import com.example.demo.dto.SignUpUserResponseDto;

public interface UserService {
    SignUpUserResponseDto save(SignUpUserRequestDto userDto) throws Exception;

    JwtResponseDto authenticateUser(LoginRequestDto loginRequestDto);
}
