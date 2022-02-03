package com.example.demo.dto;

import com.example.demo.model.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
public class SignUpUserResponseDto {
    private String email;
    private Set<Role> roles;
    private String firstName;
    private String lastName;
    private Long id;
}
