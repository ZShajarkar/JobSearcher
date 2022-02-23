package com.example.demo.mapper;

import com.example.demo.dto.MainUserInfoDto;
import com.example.demo.model.User;

@Mapper
public class MainUserInfoMapper implements DtoToModelAndModelToDtoMapper<MainUserInfoDto, User> {
    @Override
    public User toModel(MainUserInfoDto dto) {
        if (dto == null) {
            return null;
        }
        User user = new User();
        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        return user;
    }

    @Override
    public MainUserInfoDto toDto(User userModel) {
        if (userModel == null) {
            return null;
        }
        MainUserInfoDto userDto = new MainUserInfoDto();
        userDto.setFirstName(userModel.getFirstName());
        userDto.setLastName(userModel.getLastName());
        userDto.setId(userModel.getId());
        return userDto;
    }
}
