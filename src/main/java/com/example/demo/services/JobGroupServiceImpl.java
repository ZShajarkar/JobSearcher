package com.example.demo.services;

import com.example.demo.enums.JobGroup;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobGroupServiceImpl implements JobGroupService {
    @Override
    public List<String> getJobGroups() {
        List<String> jobGroups = new ArrayList<>();
        for (JobGroup jobGroup : JobGroup.values()) {
            jobGroups.add(jobGroup.value());
        }
        return jobGroups;
    }
}
