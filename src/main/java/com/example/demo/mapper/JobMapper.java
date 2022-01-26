package com.example.demo.mapper;

import com.example.demo.dto.JobDto;
import com.example.demo.model.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobMapper implements DtoToModelMapper<JobDto, Job> {
    private final CompanyMapper companyMapper;

    @Autowired
    public JobMapper(CompanyMapper companyMapper) {
        this.companyMapper = companyMapper;
    }

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
        job.setSkills(dto.getSkills());
        job.setCompany(this.companyMapper.toModel(dto.getCompany()));
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
        jobDto.setSkills(jobModel.getSkills());
        jobDto.setCompany(this.companyMapper.toDto(jobModel.getCompany()));
        return jobDto;
    }
}
