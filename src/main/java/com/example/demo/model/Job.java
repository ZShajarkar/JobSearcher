package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
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
    @Min(value = 0, message = "حقوق می بایست عدد مثبت باشد")
    private BigInteger Salary;
    @Column(nullable = false,length = 10000)
    private String jobDescription;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "job_skills",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private Set<Skill> skills = new HashSet<>();
    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Resume> resumes = new HashSet<>();

    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(nullable = false)
    private Company company;
    @CreationTimestamp
    private LocalDate registeredDate=LocalDate.now();
    @Column(columnDefinition = "boolean default false", nullable = false)
    private boolean deleted;

}
