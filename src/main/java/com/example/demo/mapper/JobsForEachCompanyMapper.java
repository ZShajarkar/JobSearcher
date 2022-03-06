package com.example.demo.mapper;

import com.example.demo.dto.JobsForEachCompanyDto;
import com.example.demo.enums.JobStatus;
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
        String newJobTitle = updateJobTitle(jobModel);
        jobDto.setJobTitle(newJobTitle);
        return jobDto;
    }

    private String updateJobTitle(Job job) {
        int jobStatusId = job.getJobStatus();
        return job.getJobTitle().concat(getStatusOfJob(jobStatusId));
    }

    private String getStatusOfJob(int id) {
        String status = " ( %s ) ";
        if (id == 1)
            status = status.formatted(JobStatus.UNDEFINED.getValue());
        else if (id == 2)
            status = status.formatted(JobStatus.REJECTED.getValue());
        else if (id == 3)
            status = status.formatted(JobStatus.APPROVED.getValue());

        return status;
    }
}
