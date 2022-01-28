package com.example.demo.configuration;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class SignupRequest {
    private String email;
    private String password;
    private Set<String> roles;
}
