package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SignUpCompanyRequestDto {
    private String companyName;
    private String city;
    private String aboutCompany;
    private String address;
    private String password;
    private String confirmPassword;
    private String email;
}

