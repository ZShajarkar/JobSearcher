package com.example.demo.services;

import com.example.demo.dto.UserDto;

public interface UserService {
    UserDto save(UserDto userDto) throws Exception;
}
