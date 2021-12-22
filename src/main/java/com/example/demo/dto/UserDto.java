package com.example.demo.dto;

import com.example.demo.model.Job;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserDto {

    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String confirmedPassword;
    private Set<ResumeDto> resumes = new HashSet<>();
}

