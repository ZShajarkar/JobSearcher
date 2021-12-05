package com.example.demo.services;

import com.example.demo.dto.UserDto;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper dtoToModelMapper;
    private final BaseService baseService;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper dtoToModelMapper, BaseService baseService) {
        this.userRepository = userRepository;
        this.dtoToModelMapper = dtoToModelMapper;
        this.baseService = baseService;
    }

    public void save(UserDto userDto) {
        userDto.setPassword(baseService.encode(userDto.getPassword()));
        userRepository.save(dtoToModelMapper.toModel(userDto));
    }
}
