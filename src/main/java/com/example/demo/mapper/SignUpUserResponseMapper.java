package com.example.demo.mapper;

import com.example.demo.dto.SignUpUserResponseDto;
import com.example.demo.model.User;

@Mapper
public class SignUpUserResponseMapper implements ModelToDtoMapper<SignUpUserResponseDto, User> {
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