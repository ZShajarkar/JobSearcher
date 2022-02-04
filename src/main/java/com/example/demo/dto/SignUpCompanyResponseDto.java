package com.example.demo.dto;

import com.example.demo.model.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class SignUpCompanyResponseDto {
    private String companyName;
    private String city;
    private String aboutCompany;
    private String address;
    private String email;
    private Set<Role> roles;
    private Long id;
}
