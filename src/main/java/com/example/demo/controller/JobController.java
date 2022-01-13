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
    }

    @GetMapping(produces = "application/json")
    public List<JobDto> findByJobTitleAndCity(@RequestParam(value = "jobTitle", required = false) String jobTitle, @RequestParam(value = "city", required = false) String city) {
        return this.jobService.findByJobTitleAndCity(jobTitle, city);
        // return  new Response(Math.toIntExact(savedDto.getId()));
    }
}
