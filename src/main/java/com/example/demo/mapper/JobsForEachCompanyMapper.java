package com.example.demo.mapper;

import com.example.demo.dto.JobsForEachCompanyDto;
import com.example.demo.model.Job;

@Mapper
public class JobsForEachCompanyMapper implements DtoToModelAndModelToDtoMapper<JobsForEachCompanyDto, Job> {


    @Override
    public Job toModel(JobsForEachCompanyDto dto) {
        if (dto == null) {
            return null;
        }
        Job job = new Job();
        job.setId(dto.getId());
        job.setJobTitle(dto.getJobTitle());
        return job;
    }

    @Override
    public JobsForEachCompanyDto toDto(Job jobModel) {
        if (jobModel == null) {
            return null;
        }
        JobsForEachCompanyDto jobDto = new JobsForEachCompanyDto();
        jobDto.setId(jobModel.getId());
        jobDto.setJobTitle(jobModel.getJobTitle());
        return jobDto;
    }
}
