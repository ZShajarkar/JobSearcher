package com.example.demo.mapper;

import com.example.demo.dto.JobDto;
import com.example.demo.dto.SkillDto;
import com.example.demo.model.Job;
import com.example.demo.model.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class JobMapper implements DtoToModelMapper<JobDto, Job> {
    private final CompanyMapper companyMapper;
    private final SkillMapper skillMapper;

    @Autowired
    public JobMapper(CompanyMapper companyMapper, SkillMapper skillMapper) {
        this.companyMapper = companyMapper;
        this.skillMapper = skillMapper;
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
        job.setCompany(this.companyMapper.toModel(dto.getCompany()));
        Set<Skill> skills = new HashSet<>(this.skillMapper.toModel(dto.getSkills()));
        job.setSkills(skills);
        job.setRegisteredDate(dto.getRegisteredDate());
        job.setDeleted(dto.isDeleted());
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
        jobDto.setCompany(this.companyMapper.toDto(jobModel.getCompany()));
        Set<SkillDto> skillDtoSet = new HashSet<>(this.skillMapper.toDto(jobModel.getSkills()));
        jobDto.setSkills(skillDtoSet);
        jobDto.setRegisteredDate(jobModel.getRegisteredDate());
        jobDto.setDeleted(jobModel.isDeleted());
        return jobDto;
    }
}
