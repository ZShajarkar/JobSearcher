package com.example.demo.services;

import com.example.demo.dto.CompanyDto;
import com.example.demo.mapper.CompanyMapper;
import com.example.demo.model.Company;
import com.example.demo.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, CompanyMapper companyMapper) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
    }

    public CompanyDto save(CompanyDto companyDto) {
        Company savedCompany = this.companyRepository.save(this.companyMapper.toModel(companyDto));
        return this.companyMapper.toDto(savedCompany);
    }
}
