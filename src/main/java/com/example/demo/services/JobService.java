package com.example.demo.services;

import com.example.demo.dto.JobDto;
import com.example.demo.dto.JobsForEachCompanyDto;

import java.util.List;

public interface JobService {
    JobDto save(JobDto jobDto, String token) throws Exception;

    List<JobDto> findByJobTitleAndCity(String jobTitle, Integer city);

    List<JobsForEachCompanyDto> findJobsByCompanyId(String token);
}
