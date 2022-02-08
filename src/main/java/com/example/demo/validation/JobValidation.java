package com.example.demo.validation;

import com.example.demo.dto.JobDto;
import com.example.demo.exception.ExceptionMessage;
import com.example.demo.model.Job;
import com.example.demo.repository.JobRepository;
import com.example.demo.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.ValidationException;
import java.util.List;

@Component
public class JobValidation {
    private final JobRepository jobRepository;
    private final SkillValidation skillValidation;

    @Autowired
    public JobValidation(JobRepository jobRepository, SkillValidation skillValidation) {
        this.jobRepository = jobRepository;
        this.skillValidation = skillValidation;
    }

    public void validateJob(JobDto jobDto) throws ValidationException {
        Validation.notEmpty(jobDto.getJobTitle(), ExceptionMessage.JOB_TITLE_MUST_BE_FILLED);
        Validation.notEmpty(jobDto.getSkills(), ExceptionMessage.JOB_SKILLS_MUST_BE_FILLED);
        Validation.notEmpty(jobDto.getJobDescription(), ExceptionMessage.ABOUT_JOB_MUST_BE_FILLED);
        Validation.notEmpty(jobDto.getJobGroup(), ExceptionMessage.JOB_GROUP_MUST_BE_FILLED);
        Validation.notNull(jobDto.getCompany().getId(), ExceptionMessage.INPUT_NOT_CORRECT);

        Validation.validatePositiveNumber(jobDto.getSalary(), ExceptionMessage.SALARY_MUST_BE_POSITIVE);

        validateUniqueJobForCompany(jobDto);

        skillValidation.saveSkillIfIdIsNull(jobDto.getSkills());
        validateActiveJobsAreLessThanFive(jobDto);
    }

    private void validateUniqueJobForCompany(JobDto jobDto) throws ValidationException {
        List<Job> byCompanyAndJobId = this.jobRepository.findByCompanyAndJobId(jobDto.getJobTitle(), jobDto.getCompany().getId());
        if (!byCompanyAndJobId.isEmpty())
            throw new ValidationException(ExceptionMessage.JOB_HAS_BEEN_DECLARED_IN_TEN_PAST_DAYS);
    }

    private void validateActiveJobsAreLessThanFive(JobDto jobDto) throws ValidationException {
        int countOfActiveJobForCompany = this.jobRepository.findCountOfActiveJobForCompany(jobDto.getCompany().getId());
        if (countOfActiveJobForCompany > Constants.ALLOWABLE_NUMBER_FOR_JOBS_ARE_5_EACH_COUNTER)
            throw new ValidationException(ExceptionMessage.MORE_THAN_TEN_JOB_HAS_BEEN_SAVED_FOR_THIS_COMPANY);
    }
}
