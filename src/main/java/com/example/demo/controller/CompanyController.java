package com.example.demo.controller;

import com.example.demo.dto.JwtResponseDto;
import com.example.demo.dto.LoginRequestDto;
import com.example.demo.dto.SignUpCompanyRequestDto;
import com.example.demo.dto.SignUpCompanyResponseDto;
import com.example.demo.services.CompanyService;
import com.example.demo.util.ResponseFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "it saves the company",
            tags = "Register",
            responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = SignUpCompanyResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "Bad request")})
    public ResponseEntity<?> save(@RequestBody SignUpCompanyRequestDto companyDto) {
        try {
            return ResponseFactory.ok(this.companyService.save(companyDto), "شرکت با موفقیت ثبت شد");
        } catch (Exception e) {
            return ResponseFactory.badRequest(e.getMessage());
        }
    }

    @PostMapping(path = "sign-in", consumes = "application/json", produces = "application/json")
    @Operation(summary = "company can log in ",
            tags = "Log in",
            responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = JwtResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "Bad request")})
    public ResponseEntity<?> signIn(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        try {
            return ResponseFactory.ok(companyService.authenticateCompany(loginRequestDto));
        } catch (Exception e) {
            return ResponseFactory.badRequest(e.getMessage());
        }
    }
}
