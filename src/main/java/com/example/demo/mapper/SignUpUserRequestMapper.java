package com.example.demo.mapper;

import com.example.demo.dto.SignUpUserRequestDto;
import com.example.demo.model.User;

@Mapper
public class SignUpUserRequestMapper implements DtoToModelMapper<SignUpUserRequestDto, User> {
    @Override
    public User toModel(SignUpUserRequestDto dto) {
        if (dto == null) {
            return null;
        }
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }
}
