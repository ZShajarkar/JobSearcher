package com.example.demo.services;

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

    @Autowired
    public JobServiceImpl(JobRepository jobRepository, JobMapper jobMapper, JobValidation jobValidation) {
        this.jobRepository = jobRepository;
        this.jobMapper = jobMapper;
        this.jobValidation = jobValidation;
    }

    public JobDto save(JobDto jobDto) throws Exception {
        jobDto.setRegisteredDate(LocalDate.now());
        jobValidation.validateJob(jobDto);
        Job savedJob = this.jobRepository.save(this.jobMapper.toModel(jobDto));
        return this.jobMapper.toDto(savedJob);
    }

    public List<JobDto> findByJobTitleAndCity(String jobTitle, String city) {
        List<Job> jobsByJobTitle = this.jobRepository.findByJobTitleAndCity(jobTitle, city);
        return this.jobMapper.toDto(jobsByJobTitle);
    }

    @Override
    @Scheduled(cron = Constants.EVERY_DAY)
    //  @Scheduled(cron = "*/10 * * * * *")
    public void deleteAfterTenDays() {
        LocalDate localDate = LocalDate.now();
        // localDate.minusDays(10);
        localDate.minusDays(0);
        List<Long> jobIds = this.jobRepository.selectExpiredJobIds(localDate);
        this.jobRepository.deleteJobSkillsAfterTenDays(jobIds);
        this.jobRepository.deleteAfterTenDays(localDate);
    }
}
