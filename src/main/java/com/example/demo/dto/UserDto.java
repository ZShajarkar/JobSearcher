package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserDto extends SignupRequest {

    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Set<ResumeDto> resumes = new HashSet<>();
}

