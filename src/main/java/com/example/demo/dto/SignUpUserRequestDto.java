package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class SignUpUserRequestDto {
    private String email;
    private String password;
    private String confirmedPassword;
    private String firstName;
    private String lastName;
}
