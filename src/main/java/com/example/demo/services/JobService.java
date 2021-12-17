package com.example.demo.services;

import com.example.demo.dto.JobDto;
import com.example.demo.mapper.JobMapper;
import com.example.demo.model.Job;
import com.example.demo.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {
    private final JobRepository jobRepository;
    private final JobMapper jobMapper;

    @Autowired
    public JobService(JobRepository jobRepository, JobMapper jobMapper) {
        this.jobRepository = jobRepository;
        this.jobMapper = jobMapper;
    }

    public JobDto save(JobDto jobDto) {
        Job savedJob = this.jobRepository.save(this.jobMapper.toModel(jobDto));
         return this.jobMapper.toDto(savedJob);
    }
}
