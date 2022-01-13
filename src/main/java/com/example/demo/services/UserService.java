package com.example.demo.services;

import com.example.demo.dto.UserDto;

public interface UserService {
    void save(UserDto userDto) throws Exception;
}
