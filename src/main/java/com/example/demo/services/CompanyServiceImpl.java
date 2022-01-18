package com.example.demo.services;

import com.example.demo.dto.CompanyDto;
import com.example.demo.mapper.CompanyMapper;
import com.example.demo.model.Company;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.validation.CompanyValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final CompanyValidation companyValidation;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, CompanyMapper companyMapper, CompanyValidation companyValidation) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
        this.companyValidation = companyValidation;
    }


    @Override
    public CompanyDto save(CompanyDto companyDto) throws ValidationException {
        companyValidation.validate(companyDto);
        Company savedCompany = this.companyRepository.save(this.companyMapper.toModel(companyDto));
        return this.companyMapper.toDto(savedCompany);
    }
}
