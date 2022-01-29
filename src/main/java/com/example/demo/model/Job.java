package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
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
    @Column(nullable = false)
    private LocalDate registeredDate;
    @Column(columnDefinition = "boolean default false", nullable = false)
    private boolean deleted;

}
