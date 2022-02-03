package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class SignupRequest {
    private String email;
    private String password;
    private Set<String> roles;
    private String confirmedPassword;

}
