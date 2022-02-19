package com.example.demo.controller;

import com.example.demo.dto.LoginRequestDto;
import com.example.demo.dto.SignUpCompanyRequestDto;
import com.example.demo.services.CompanyService;
import com.example.demo.util.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/public/company/v1")
public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> save(@RequestBody SignUpCompanyRequestDto companyDto) {
        try {
            return ResponseFactory.ok(this.companyService.save(companyDto), "شرکت با موفقیت ثبت شد");
        } catch (Exception e) {
            return ResponseFactory.badRequest(e.getMessage());
        }
    }

    @PostMapping(path = "sign-in", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> processRegistration(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        try {
            return ResponseFactory.ok(companyService.authenticateCompany(loginRequestDto));
        } catch (Exception e) {
            return ResponseFactory.badRequest(e.getMessage());
        }
    }
}
