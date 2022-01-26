package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "Job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String JobTitle;
    @Column(nullable = false, length = 100)
    private String JobGroup;
    @Column(nullable = true)
    @Min(value = 0,message = "حقوق می بایست عدد مثبت باشد")
    private BigInteger Salary;
    @Column(nullable = false, length = 100)
    private String jobDescription;
    @ElementCollection
    @Column(nullable = false, length = 100)
    private List<String> skills;
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

    public BigInteger getSalary() {
        return Salary;
    }

    public void setSalary(BigInteger salary) {
        Salary = salary;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skill) {
        this.skills = skill;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
