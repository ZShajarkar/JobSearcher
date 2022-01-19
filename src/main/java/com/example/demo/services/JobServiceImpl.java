package com.example.demo.services;

import com.example.demo.dto.JobDto;
import com.example.demo.mapper.JobMapper;
import com.example.demo.model.Job;
import com.example.demo.repository.JobRepository;
import com.example.demo.validation.JobValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final JobMapper jobMapper;
    private final JobValidation jobValidation;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository, JobMapper jobMapper, JobValidation jobValidation) {
        this.jobRepository = jobRepository;
        this.jobMapper = jobMapper;
        this.jobValidation = jobValidation;
    }

    public JobDto save(JobDto jobDto) throws Exception {
        jobValidation.validateJob(jobDto);
        Job savedJob = this.jobRepository.save(this.jobMapper.toModel(jobDto));
        return this.jobMapper.toDto(savedJob);
    }

    public List<JobDto> findByJobTitleAndCity(String jobTitle, String city) {
        List<Job> jobsByJobTitle = this.jobRepository.findByJobTitleAndCity(jobTitle, city);
        return this.jobMapper.toDto(jobsByJobTitle);
    }
}
