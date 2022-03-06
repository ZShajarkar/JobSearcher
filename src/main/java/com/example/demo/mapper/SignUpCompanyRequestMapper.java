package com.example.demo.mapper;

import com.example.demo.dto.SignUpCompanyRequestDto;
import com.example.demo.model.Company;

@Mapper
public class SignUpCompanyRequestMapper implements DtoToModelMapper<SignUpCompanyRequestDto, Company> {
    @Override
    public Company toModel(SignUpCompanyRequestDto dto) {
        if (dto == null) {
            return null;
        }
        Company company = new Company();
        company.setUserName(dto.getEmail());
        company.setPassword(dto.getPassword());
        company.setCity(dto.getCity());
        company.setAboutCompany(dto.getAboutCompany());
        company.setCompanyName(dto.getCompanyName());
        company.setAddress(dto.getAddress());
        return company;
    }
}
