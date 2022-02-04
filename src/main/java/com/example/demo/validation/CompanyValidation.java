package com.example.demo.validation;

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
        Validation.notEmpty(companyDto.getPassword(), ExceptionMessage.PASSWORD_MUST_BE_FILLED);
        Validation.notEmpty(companyDto.getConfirmPassword(), ExceptionMessage.PASSWORD_DOESNT_MATCH);
        Validation.notEmpty(companyDto.getEmail(), ExceptionMessage.EMAIL_MUST_BE_FILLED);

        validateUniqueCompanyAndCity(companyDto.getCompanyName(), companyDto.getCity());

        Validation.validateIfPersian(companyDto.getCompanyName(), ExceptionMessage.COMPANY_NAME_MUST_BE_LETTER);
        Validation.validateEmail(companyDto.getEmail());
        validateUniqueEmail(companyDto.getEmail());
        Validation.checkComplicatePassword(companyDto.getPassword());
        Validation.notEqual(companyDto.getConfirmPassword(), companyDto.getPassword(), ExceptionMessage.PASSWORD_DOESNT_MATCH);
    }

    private void validateUniqueCompanyAndCity(String company, String city) throws ValidationException {
        if (companyRepository.findByCompanyAndCity(company, city) != null)
            throw new ValidationException(ExceptionMessage.COMPANY_AND_NAME_HAVE_BEEN_REGISTERED);
    }

    private void validateUniqueEmail(String email) throws ValidationException {
        if (companyRepository.findByEmail(email.concat("@company")) != null)
            throw new ValidationException(ExceptionMessage.EMAIL_HAS_BEEN_REGISTERED);
    }
}
