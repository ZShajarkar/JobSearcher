package com.example.demo.mapper;

import com.example.demo.dto.CompanyDto;
import com.example.demo.model.Company;

@Mapper
public class CompanyMapper implements DtoToModelAndModelToDtoMapper<CompanyDto, Company> {

    @Override
    public Company toModel(CompanyDto dto) {
        if (dto == null) {
            return null;
        }
        Company company = new Company();
        company.setAboutCompany(dto.getAboutCompany());
        company.setCompanyName(dto.getCompanyName());
        company.setAddress(dto.getAddress());
        company.setId(dto.getId());
        return company;
    }

    @Override
    public CompanyDto toDto(Company vm) {
        if (vm == null) {
            return null;
        }
        CompanyDto companyDto = new CompanyDto();
        companyDto.setAboutCompany(vm.getAboutCompany());
        companyDto.setCompanyName(vm.getCompanyName());
        companyDto.setAddress(vm.getAddress());
        companyDto.setCity(vm.getCity());
        companyDto.setId(vm.getId());
        return companyDto;
    }
}
