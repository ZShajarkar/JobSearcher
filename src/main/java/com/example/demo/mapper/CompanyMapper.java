package com.example.demo.mapper;

import com.example.demo.dto.CompanyDto;
import com.example.demo.model.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper implements DtoToModelMapper<CompanyDto, Company> {
    @Override
    public Company toModel(CompanyDto dto) {
        if (dto == null) {
            return null;
        }
        Company company = new Company();
        company.setAboutCompany(dto.getAboutCompany());
        company.setCompanyName(dto.getCompanyName());
        company.setAddress(dto.getAddress());
        company.setCity(dto.getCity());
        company.setJobs(dto.getJobs());
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
        companyDto.setJobs(vm.getJobs());
        companyDto.setId(vm.getId());
        return companyDto;
    }
}
