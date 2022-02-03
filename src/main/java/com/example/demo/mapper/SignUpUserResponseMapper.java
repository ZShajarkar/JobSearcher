package com.example.demo.mapper;

import com.example.demo.dto.SignUpUserResponseDto;
import com.example.demo.model.User;
import org.springframework.stereotype.Component;

@Component
public class SignUpUserResponseMapper implements DtoToModelMapper<SignUpUserResponseDto, User> {
    @Override
    public User toModel(SignUpUserResponseDto dto) {
        return null;
    }

    @Override
    public SignUpUserResponseDto toDto(User userModel) {
        if (userModel == null) {
            return null;
        }
        SignUpUserResponseDto userDto = new SignUpUserResponseDto();
        userDto.setFirstName(userModel.getFirstName());
        userDto.setLastName(userModel.getLastName());
        userDto.setEmail(userModel.getEmail());
        userDto.setId(userModel.getId());
        userDto.setRoles(userModel.getRoles());
        return userDto;
    }
}