package com.example.demo.services;

import com.example.demo.dto.CompanyDto;
import com.example.demo.dto.JobDto;
import com.example.demo.mapper.JobMapper;
import com.example.demo.model.Job;
import com.example.demo.repository.JobRepository;
import com.example.demo.util.Constants;
import com.example.demo.validation.JobValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@EnableScheduling
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final JobMapper jobMapper;
    private final JobValidation jobValidation;
    private final AuthenticationService authenticationService;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository, JobMapper jobMapper, JobValidation jobValidation, AuthenticationService authenticationService) {
        this.jobRepository = jobRepository;
        this.jobMapper = jobMapper;
        this.jobValidation = jobValidation;
        this.authenticationService = authenticationService;
    }

    public JobDto save(JobDto jobDto, String token) throws Exception {
        jobDto.setCompany(new CompanyDto(authenticationService.getIdOutOfBearerToken(token)));
        jobValidation.validateJob(jobDto);
        Job savedJob = this.jobRepository.save(this.jobMapper.toModel(jobDto));
        return this.jobMapper.toDto(savedJob);
    }

    public List<JobDto> findByJobTitleAndCity(String jobTitle, String city) {
        List<Job> jobsByJobTitle = this.jobRepository.findByJobTitleAndCity(jobTitle, city);
        return this.jobMapper.toDto(jobsByJobTitle);
    }

    @Scheduled(cron = Constants.EVERY_DAY)
    // @Scheduled(cron = "*/10 * * * * *")
    public void deleteAfterTenDays() {
        LocalDate tenDaysAgo = LocalDate.now().minusDays(10);
        // localDate.minusDays(0);
        this.jobRepository.deleteAfterTenDays(tenDaysAgo);
    }
}
