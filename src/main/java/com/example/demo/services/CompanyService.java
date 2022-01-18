package com.example.demo.services;

import com.example.demo.dto.CompanyDto;

import javax.xml.bind.ValidationException;

public interface CompanyService {
    CompanyDto save(CompanyDto companyDto) throws ValidationException;
}
