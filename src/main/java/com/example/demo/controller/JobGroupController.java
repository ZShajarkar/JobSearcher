package com.example.demo.controller;

import com.example.demo.services.JobGroupService;
import com.example.demo.util.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@CrossOrigin
@RestController
@RequestMapping("/public/job-groups/v1")
public class JobGroupController {
    private final JobGroupService jobGroupService;

    @Autowired
    public JobGroupController(JobGroupService jobGroupService) {
        this.jobGroupService = jobGroupService;
    }

    @GetMapping()
    public ResponseEntity<?> getJobGroups() {
        try {
            return ResponseFactory.ok(jobGroupService.getJobGroups());
        } catch (Exception e) {
            return ResponseFactory.handel((HttpClientErrorException) e);
        }
    }
}
