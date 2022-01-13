package com.example.demo.services;

import com.example.demo.dto.JobDto;

import java.util.List;

public interface JobService {
    JobDto save(JobDto jobDto);

    List<JobDto> findByJobTitleAndCity(String jobTitle, String city);
}
