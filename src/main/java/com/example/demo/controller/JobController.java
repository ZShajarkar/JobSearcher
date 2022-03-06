package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.services.JobService;
import com.example.demo.util.ResponseFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/public/job/v1")
@SecurityScheme(name = "Authorization", scheme = "Bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class JobController {
    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }


    @PostMapping(consumes = "application/json", produces = "application/json")
    @PreAuthorize("hasRole('COMPANY')")
    @Operation(summary = "Company can define an job using token",
            tags = "Company Operation",
            security = @SecurityRequirement(name = "Authorization"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = JobDto.class))),
                    @ApiResponse(responseCode = "400", description = "Bad request")})

    public ResponseEntity<?> save(@RequestBody JobDto jobDto, @RequestHeader(value = "Authorization") String token) {
        try {
            return ResponseFactory.ok(this.jobService.save(jobDto, token));
        } catch (Exception exception) {
            return ResponseFactory.badRequest(exception.getMessage());
        }
    }

    @GetMapping(produces = "application/json")
    @Operation(summary = "Jobs are searched by this method",
            tags = "Common operations ",
            responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = JobStos.class))),
                    @ApiResponse(responseCode = "400", description = "Bad request")})
    public ResponseEntity<?> findByJobTitleAndCity(@RequestParam(value = "jobTitle", required = false) String jobTitle, @RequestParam(value = "city", required = false) Integer city) {
        try {
            return ResponseFactory.ok(this.jobService.findByJobTitleAndCity(jobTitle, city));
        } catch (Exception exception) {
            return ResponseFactory.badRequest(exception.getMessage());
        }
    }

    @GetMapping(path = "/companyId", produces = "application/json")
    @PreAuthorize("hasRole('COMPANY')")
    @Operation(summary = "get jobs of a company using token",
            tags = "Company Operation",
            security = @SecurityRequirement(name = "Authorization"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = JobsForEachCompanyDtos.class))),
                    @ApiResponse(responseCode = "400", description = "Bad request")})
    public ResponseEntity<?> findByCompanyId(@RequestHeader("Authorization") String token) {
        try {
            return ResponseFactory.ok(this.jobService.findJobsByCompanyId(token));
        } catch (Exception exception) {
            return ResponseFactory.badRequest(exception.getMessage());
        }
    }
}
