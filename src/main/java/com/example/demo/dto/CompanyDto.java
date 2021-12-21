package com.example.demo.dto;

import com.example.demo.model.Job;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class CompanyDto {
    private Long id;
    private String companyName;
    private String city;
    private String aboutCompany;
    private String address;
    private Set<Job> jobs = new HashSet<>();
}
