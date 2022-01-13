package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "Job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true, columnDefinition = "nvarchar", length = 100)
    private String JobTitle;
    @Column(nullable = true, columnDefinition = "nvarchar", length = 100)
    private String JobGroup;
    @Column(nullable = true, columnDefinition = "nvarchar", length = 100)
    private String Salary;
    @Column(nullable = true, columnDefinition = "nvarchar", length = 100)
    private String jobDescription;
    @Column(nullable = true, columnDefinition = "nvarchar", length = 100)
    private String skill;
    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Resume> resumes = new HashSet<>();

    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(nullable = false)
    private Company company;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobTitle() {
        return JobTitle;
    }

    public void setJobTitle(String jobTitle) {
        JobTitle = jobTitle;
    }

    public String getJobGroup() {
        return JobGroup;
    }

    public void setJobGroup(String jobGroup) {
        JobGroup = jobGroup;
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String salary) {
        Salary = salary;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
