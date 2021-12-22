package com.example.demo.mapper;

import com.example.demo.model.User;
import com.example.demo.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements DtoToModelMapper<UserDto, User> {
    @Override
    public User toModel(UserDto dto) {
        if (dto == null) {
            return null;
        }
        User user=new User();
        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }

    @Override
    public UserDto toDto(User userModel) {
        if (userModel == null) {
            return null;
        }
        UserDto userDto=new UserDto();
        userDto.setFirstName(userModel.getFirstName());
        userDto.setLastName(userModel.getLastName());
        userDto.setEmail(userModel.getEmail());
        userDto.setPassword(userModel.getPassword());
        return userDto;
    }
}
