package com.example.demo.validation;

import com.example.demo.dto.CompanyDto;
import com.example.demo.exception.ExceptionMessage;
import com.example.demo.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.ValidationException;

@Component
public class CompanyValidation {
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyValidation(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public void validate(CompanyDto companyDto) throws ValidationException {
        validateUniqueCompanyAndCity(companyDto.getCompanyName(), companyDto.getCity());
    }

    private void validateUniqueCompanyAndCity(String company, String city) throws ValidationException {
        if (companyRepository.findByCompanyAndCity(company, city) != null)
            throw new ValidationException(ExceptionMessage.COMPANY_AND_NAME_HAVE_BEEN_REGISTERED);
    }
}
