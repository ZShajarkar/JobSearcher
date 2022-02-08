package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class JobDto {
    private Long id;
    private String jobTitle;
    private String jobGroup;
    private BigInteger salary;
    private String jobDescription;
    private Set<SkillDto> skills = new HashSet<>();
    private CompanyDto company;
    private Set<ResumeDto> resumes = new HashSet<>();
    private LocalDate registeredDate;
    private boolean deleted;
}
