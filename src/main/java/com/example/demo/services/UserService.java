package com.example.demo.services;

import com.example.demo.dto.*;

import java.util.List;

public interface UserService {
    SignUpUserResponseDto save(SignUpUserRequestDto userDto) throws Exception;

    JwtResponseDto authenticateUser(LoginRequestDto loginRequestDto);

    List<MainUserInfoDto> getUsersSentResume(Long jobId, String token) throws Exception;
}
