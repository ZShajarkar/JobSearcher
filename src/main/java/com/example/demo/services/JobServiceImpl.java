package com.example.demo.services;

import com.example.demo.dto.CompanyDto;
import com.example.demo.dto.JobDto;
import com.example.demo.dto.JobsForEachCompanyDto;
import com.example.demo.mapper.JobMapper;
import com.example.demo.mapper.JobsForEachCompanyMapper;
import com.example.demo.model.Job;
import com.example.demo.repository.JobRepository;
import com.example.demo.util.Constants;
import com.example.demo.validation.JobValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Service
@EnableScheduling
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final JobMapper jobMapper;
    private final JobsForEachCompanyMapper jobsForEachCompanyMapper;
    private final JobValidation jobValidation;
    private final AuthenticationService authenticationService;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository, JobMapper jobMapper, JobsForEachCompanyMapper jobsForEachCompanyMapper, JobValidation jobValidation, AuthenticationService authenticationService) {
        this.jobRepository = jobRepository;
        this.jobMapper = jobMapper;
        this.jobsForEachCompanyMapper = jobsForEachCompanyMapper;
        this.jobValidation = jobValidation;
        this.authenticationService = authenticationService;
    }

    public JobDto save(JobDto jobDto, String token) throws Exception {
        jobDto.getSkills().stream().filter(item -> item.getId() != null && item.getId() == 0).forEach(item -> item.setId(null));
        jobDto.setCompany(new CompanyDto(authenticationService.getIdOutOfBearerToken(token)));
        jobValidation.validateJob(jobDto);
        Job job = this.jobMapper.toModel(jobDto);
        Job savedJob = this.jobRepository.save(job);
        return this.jobMapper.toDto(savedJob);
    }

    public List<JobDto> findByJobTitleAndCity(String jobTitle, Integer city) {
        List<Job> jobsByJobTitle = this.jobRepository.findByJobTitleAndCity(jobTitle, city);
        return (List<JobDto>) this.jobMapper.toDto(jobsByJobTitle);
    }

    @Override
    public List<JobsForEachCompanyDto> findJobsByCompanyId(String token) {
        Long companyId = authenticationService.getIdOutOfBearerToken(token);
        List<Job> jobsByCompanyId = this.jobRepository.findByCompanyId(companyId);
        Collection<JobsForEachCompanyDto> jobsForEachCompanyDtos = this.jobsForEachCompanyMapper.toDto(jobsByCompanyId);
        return jobsForEachCompanyDtos.stream().toList();
    }

    @Scheduled(cron = Constants.EVERY_NIGHT)
    public void deleteAfterTenDays() {
        LocalDate tenDaysAgo = LocalDate.now().minusDays(10);
        this.jobRepository.deleteAfterEqualDays(tenDaysAgo);
    }


}
