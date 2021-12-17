package com.example.demo.mapper;

import com.example.demo.dto.JobDto;
import com.example.demo.model.Job;
import org.springframework.stereotype.Component;

@Component
public class JobMapper implements DtoToModelMapper<JobDto, Job> {
    @Override
    public Job toModel(JobDto dto) {
        if (dto == null) {
            return null;
        }
        Job job = new Job();
        job.setId(dto.getId());
        job.setJobGroup(dto.getJobGroup());
        job.setJobDescription(dto.getJobDescription());
        job.setJobTitle(dto.getJobTitle());
        job.setSalary(dto.getSalary());
        job.setSkill(dto.getSkill());
        return job;
    }

    @Override
    public JobDto toDto(Job jobModel) {
        if (jobModel == null) {
            return null;
        }
        JobDto jobDto = new JobDto();
        jobDto.setId(jobModel.getId());
        jobDto.setJobGroup(jobModel.getJobGroup());
        jobDto.setJobDescription(jobModel.getJobDescription());
        jobDto.setJobTitle(jobModel.getJobTitle());
        jobDto.setSalary(jobModel.getSalary());
        jobDto.setSkill(jobModel.getSkill());
        return jobDto;
    }
}