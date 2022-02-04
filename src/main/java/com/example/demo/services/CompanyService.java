package com.example.demo.services;

import com.example.demo.dto.*;

import javax.xml.bind.ValidationException;

public interface CompanyService {
    SignUpCompanyResponseDto save(SignUpCompanyRequestDto companyDto) throws ValidationException;
    JwtResponseDto authenticateUser(LoginRequestDto loginRequestDto);
}
