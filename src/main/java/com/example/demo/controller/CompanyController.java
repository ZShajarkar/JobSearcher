package com.example.demo.controller;

import com.example.demo.dto.CompanyDto;
import com.example.demo.response.ResponseMessage;
import com.example.demo.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ResponseMessage> save(@RequestBody CompanyDto companyDto) {
        try {
            this.companyService.save(companyDto);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("شرکت با موفقیت ثبت شد"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(e.getMessage()));
        }
    }
}
