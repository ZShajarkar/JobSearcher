package com.example.demo.services;

import com.example.demo.model.Job;

import java.util.List;

public interface JobStatusDeterminationService {
    List<Job> getUndefinedStatusJobs();
    void approveJobs(List<Long> undefinedStatusJobs);
    void rejectJobs(List<Long> undefinedStatusJobs);
}
