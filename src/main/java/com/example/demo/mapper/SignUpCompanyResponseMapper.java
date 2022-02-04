package com.example.demo.mapper;

import com.example.demo.dto.SignUpCompanyResponseDto;
import com.example.demo.model.Company;
import org.springframework.stereotype.Component;

@Component
public class SignUpCompanyResponseMapper implements DtoToModelMapper<SignUpCompanyResponseDto, Company> {
    @Override
    public Company toModel(SignUpCompanyResponseDto dto) {
        return null;
    }

    @Override
    public SignUpCompanyResponseDto toDto(Company userModel) {
        if (userModel == null) {
            return null;
        }
        SignUpCompanyResponseDto companyDto = new SignUpCompanyResponseDto();
        companyDto.setCompanyName(userModel.getCompanyName());
        companyDto.setCity(userModel.getCity());
        companyDto.setEmail(userModel.getEmail());
        companyDto.setId(userModel.getId());
        companyDto.setRoles(userModel.getRoles());
        companyDto.setAddress(userModel.getAddress());
        return companyDto;
    }
}