package com.example.demo.validation;

import com.example.demo.dto.JobDto;
import com.example.demo.exception.ExceptionMessage;
import org.springframework.stereotype.Component;

@Component
public class JobValidation {
    public void validateJob(JobDto jobDto) throws Exception {
        Validation.notEmpty(jobDto.getJobTitle(), ExceptionMessage.JOB_TITLE_MUST_BE_FILLED);
        Validation.notEmpty(jobDto.getSkills(), ExceptionMessage.JOB_SKILLS_MUST_BE_FILLED);
        Validation.notEmpty(jobDto.getJobDescription(), ExceptionMessage.ABOUT_JOB_MUST_BE_FILLED);
        Validation.notEmpty(jobDto.getJobGroup(), ExceptionMessage.JOB_GROUP_MUST_BE_FILLED);
        Validation.notNull(jobDto.getCompany().getId(), ExceptionMessage.INPUT_NOT_CORRECT);

        Validation.validatePositiveNumber(jobDto.getSalary(), ExceptionMessage.SALARY_MUST_BE_POSITIVE);
    }
}
