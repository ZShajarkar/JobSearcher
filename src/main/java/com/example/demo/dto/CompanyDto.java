package com.example.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CompanyDto {
    private Long id;
    private String companyName;
    private int city;
    private String aboutCompany;
    private String address;
    private Set<JobDto> jobs = new HashSet<>();

    public CompanyDto(Long id) {
        this.id = id;
    }
}
