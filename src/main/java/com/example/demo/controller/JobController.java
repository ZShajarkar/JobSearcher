package com.example.demo.controller;

import com.example.demo.dto.JobDto;
import com.example.demo.services.JobService;
import org.apache.catalina.connector.Response;
import org.apache.catalina.connector.ResponseFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
