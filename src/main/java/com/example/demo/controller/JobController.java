package com.example.demo.controller;

import com.example.demo.dto.JobDto;
import com.example.demo.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/job/v1")
public class JobController {
    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }


    @PostMapping(consumes = "application/json", produces = "application/json")
    public JobDto save(@RequestBody JobDto jobDto) {
        return this.jobService.save(jobDto);
        // return  new Response(Math.toIntExact(savedDto.getId()));
    }

    @GetMapping(produces = "application/json")
    public List<JobDto> findByJobTitle(@RequestParam(value = "jobTitle", required = false) String jobTitle) {
        return this.jobService.findByJobTitle(jobTitle);
        // return  new Response(Math.toIntExact(savedDto.getId()));
    }
}
