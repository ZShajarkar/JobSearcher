package com.example.demo.mapper;

import com.example.demo.dto.SignUpCompanyResponseDto;
import com.example.demo.model.Company;
import com.example.demo.services.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public class CompanyResponseMapper implements DtoToModelAndModelToDtoMapper<SignUpCompanyResponseDto, Company> {
    private final ProvinceService provinceService;

    @Autowired
    public CompanyResponseMapper(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

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
        companyDto.setCity(provinceService.getNameOfProvince(userModel.getCity()));
        companyDto.setEmail(userModel.getUserName());
        companyDto.setId(userModel.getId());
        companyDto.setRoles(userModel.getRoles());
        companyDto.setAddress(userModel.getAddress());
        return companyDto;
    }
}