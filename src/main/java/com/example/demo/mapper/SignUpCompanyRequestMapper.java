package com.example.demo.mapper;

import com.example.demo.dto.SignUpCompanyRequestDto;
import com.example.demo.model.Company;
import org.springframework.stereotype.Component;

@Component
public class SignUpCompanyRequestMapper implements DtoToModelMapper<SignUpCompanyRequestDto, Company> {
    @Override
    public Company toModel(SignUpCompanyRequestDto dto) {
        if (dto == null) {
            return null;
        }
        Company company = new Company();
        company.setEmail(dto.getEmail());
        company.setPassword(dto.getPassword());
        company.setCity(dto.getCity());
        company.setAboutCompany(dto.getAboutCompany());
        company.setCompanyName(dto.getCompanyName());
        company.setAddress(dto.getAddress());
        return company;
    }

    @Override
    public SignUpCompanyRequestDto toDto(Company vm) {
        return null;
    }
}
