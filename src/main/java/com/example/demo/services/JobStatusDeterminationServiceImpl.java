package com.example.demo.services;

import com.example.demo.enums.JobStatus;
import com.example.demo.model.Job;
import com.example.demo.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobStatusDeterminationServiceImpl implements JobStatusDeterminationService {
    private final JobRepository jobRepository;

    @Autowired
    public JobStatusDeterminationServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> getUndefinedStatusJobs() {
        return jobRepository.findByJobStatus(JobStatus.UNDEFINED.id());
    }

    @Override
    public void approveJobs(List<Long> undefinedStatusJobs) {
        jobRepository.updateJobByJobStatus(JobStatus.APPROVED.id(), undefinedStatusJobs);
    }

    @Override
    public void rejectJobs(List<Long> undefinedStatusJobs) {
        jobRepository.updateJobByJobStatus(JobStatus.REJECTED.id(), undefinedStatusJobs);
    }
}
