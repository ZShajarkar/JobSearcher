package com.example.demo.validation;

import com.example.demo.dto.CompanyDto;
import com.example.demo.dto.SignUpCompanyRequestDto;
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

    public void validate(SignUpCompanyRequestDto companyDto) throws ValidationException {
        Validation.notEmpty(companyDto.getCompanyName(), ExceptionMessage.COMPANY_NAME_IS_REQUIRED);
        Validation.notEmpty(companyDto.getAboutCompany(), ExceptionMessage.ABOUT_COMPANY_IS_REQUIRED);
        Validation.notEmpty(companyDto.getAddress(), ExceptionMessage.COMPANY_ADDRESS_IS_REQUIRED);

        validateUniqueCompanyAndCity(companyDto.getCompanyName(), companyDto.getCity());

        Validation.validateIfPersian(companyDto.getCompanyName(), ExceptionMessage.COMPANY_NAME_MUST_BE_LETTER);
    }

    private void validateUniqueCompanyAndCity(String company, String city) throws ValidationException {
        if (companyRepository.findByCompanyAndCity(company, city) != null)
            throw new ValidationException(ExceptionMessage.COMPANY_AND_NAME_HAVE_BEEN_REGISTERED);
    }
}
