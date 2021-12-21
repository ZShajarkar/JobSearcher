package com.example.demo.controller;

import com.example.demo.dto.CompanyDto;
import com.example.demo.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/company/v1")
public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public CompanyDto save(@RequestBody CompanyDto companyDto) {
        return this.companyService.save(companyDto);
    }
}
