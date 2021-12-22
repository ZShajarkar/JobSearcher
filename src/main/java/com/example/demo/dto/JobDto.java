package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class JobDto {
    private Long id;
    private String jobTitle;
    private String jobGroup;
    private String salary;
    private String jobDescription;
    private String skill;
    private CompanyDto company;
    private Set<ResumeDto> resumes = new HashSet<>();
}
