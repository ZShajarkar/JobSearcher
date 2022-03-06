package com.example.demo.controller;

import com.example.demo.dto.JobStos;
import com.example.demo.services.JobStatusDeterminationService;
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

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/public/job/status/determination/v1")
@SecurityScheme(name = "Authorization", scheme = "Bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class JobStatusDeterminationController {
    private final JobStatusDeterminationService jobStatusDeterminationService;

    @Autowired
    public JobStatusDeterminationController(JobStatusDeterminationService jobStatusDeterminationService) {
        this.jobStatusDeterminationService = jobStatusDeterminationService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(produces = "application/json")
    @Operation(summary = "Admin can see undefined job status",
            security = @SecurityRequirement(name = "Authorization"),
            tags = "admin jobs",
            responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = JobStos.class))),
                    @ApiResponse(responseCode = "400", description = "Bad request")})

    public ResponseEntity<?> getUndefinedStatusJobs() {
        try {
            return ResponseFactory.ok(this.jobStatusDeterminationService.getUndefinedStatusJobs());
        } catch (Exception exception) {
            return ResponseFactory.badRequest(exception.getMessage());
        }
    }


    @PutMapping(path = "approval")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Admin can see approve jobs ",
            tags = "admin jobs",
            security = @SecurityRequirement(name = "Authorization"),
            responses = {
                    @ApiResponse(responseCode = "204", description = "موفقیت آمیز بود"),
                    @ApiResponse(responseCode = "400", description = "Bad request")})

    public ResponseEntity<?> approveJobs(@RequestBody List<Long> jobs) {
        try {
            this.jobStatusDeterminationService.approveJobs(jobs);
            return ResponseFactory.noContent("موفقیت آمیز بود");
        } catch (Exception exception) {
            return ResponseFactory.badRequest(exception.getMessage());
        }
    }

    @PutMapping(path = "rejection")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Admin can  reject jobs ",
            tags = "admin jobs",
            security = @SecurityRequirement(name = "Authorization"),
            responses = {
                    @ApiResponse(responseCode = "204", description = "موفقیت آمیز بود"),
                    @ApiResponse(responseCode = "400", description = "Bad request")})

    public ResponseEntity<?> rejectJobs(@RequestBody List<Long> jobs) {
        try {
            this.jobStatusDeterminationService.rejectJobs(jobs);
            return ResponseFactory.noContent("موفقیت آمیز بود");
        } catch (Exception exception) {
            return ResponseFactory.badRequest(exception.getMessage());
        }
    }

}
