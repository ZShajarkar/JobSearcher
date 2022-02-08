package com.example.demo.services;

import com.example.demo.dto.JwtResponseDto;
import com.example.demo.dto.LoginRequestDto;
import com.example.demo.dto.SignUpCompanyRequestDto;
import com.example.demo.dto.SignUpCompanyResponseDto;
import com.example.demo.enums.ERole;
import com.example.demo.mapper.SignUpCompanyRequestMapper;
import com.example.demo.mapper.CompanyResponseMapper;
import com.example.demo.model.Company;
import com.example.demo.model.Role;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.validation.CompanyValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.HashSet;
import java.util.Set;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyResponseMapper companyMapper;
    private final CompanyValidation companyValidation;
    private final PasswordEncoder encoder;
    private final SignUpCompanyRequestMapper signUpCompanyRequestMapper;
    private final RoleRepository roleRepository;
    private final AuthenticationService authenticationService;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, CompanyResponseMapper companyMapper, CompanyValidation companyValidation, PasswordEncoder encoder, SignUpCompanyRequestMapper signUpCompanyRequestMapper, RoleRepository roleRepository, AuthenticationService authenticationService) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
        this.companyValidation = companyValidation;
        this.encoder = encoder;
        this.signUpCompanyRequestMapper = signUpCompanyRequestMapper;
        this.roleRepository = roleRepository;
        this.authenticationService = authenticationService;
    }


    @Override
    public SignUpCompanyResponseDto save(SignUpCompanyRequestDto companyDto) throws ValidationException {
        companyValidation.validate(companyDto);
        companyDto.setEmail(companyDto.getEmail().concat("@company"));
        companyDto.setPassword(encoder.encode(companyDto.getPassword()));
        Company company = signUpCompanyRequestMapper.toModel(companyDto);
        Set<Role> roles = new HashSet<>();
        Role companyRole = roleRepository.findByName(ERole.ROLE_COMPANY)
                .orElseThrow(() -> new RuntimeException("Error:ROLE_COMPANY is not found."));
        roles.add(companyRole);
        company.setRoles(roles);
        Company savedCompany = companyRepository.save(company);
        return this.companyMapper.toDto(savedCompany);
    }


    public JwtResponseDto authenticateUser(LoginRequestDto loginRequestDto) {
        loginRequestDto.setEmail(loginRequestDto.getEmail().concat("@company"));
        return authenticationService.authenticate(loginRequestDto);
    }
}
