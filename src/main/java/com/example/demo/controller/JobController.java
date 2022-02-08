package com.example.demo.controller;

import com.example.demo.dto.JobDto;
import com.example.demo.services.JobService;
import com.example.demo.util.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/public/job/v1")
public class JobController {
    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }


    @PostMapping(consumes = "application/json", produces = "application/json")
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<?> save(@RequestBody JobDto jobDto, @RequestHeader("Authorization") String token) {
        try {
            return ResponseFactory.ok(this.jobService.save(jobDto, token));
        } catch (Exception exception) {
            return ResponseFactory.badRequest(exception.getMessage());
        }
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> findByJobTitleAndCity(@RequestParam(value = "jobTitle", required = false) String jobTitle, @RequestParam(value = "city", required = false) int city) {
        try {
            return ResponseFactory.ok(this.jobService.findByJobTitleAndCity(jobTitle, city));
        } catch (Exception exception) {
            return ResponseFactory.badRequest(exception.getMessage());
        }
    }
}
