package com.example.demo.controller;

import com.example.demo.dto.CompanyDto;
import com.example.demo.services.CompanyService;
import com.example.demo.util.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> save(@RequestBody CompanyDto companyDto) {
        try {
            return ResponseFactory.ok(this.companyService.save(companyDto), "شرکت با موفقیت ثبت شد");
        } catch (Exception e) {
            return ResponseFactory.badRequest(e.getMessage());
        }
    }
}
