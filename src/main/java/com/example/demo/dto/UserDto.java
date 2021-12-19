package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String confirmedPassword;
}

