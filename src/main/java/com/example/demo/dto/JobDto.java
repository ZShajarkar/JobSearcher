package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

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
}
