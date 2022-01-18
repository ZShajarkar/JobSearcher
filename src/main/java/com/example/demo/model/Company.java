package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Company",uniqueConstraints = { @UniqueConstraint(columnNames = { "companyName", "city" }) })
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 45)
    private String companyName;
    @Column(nullable = false, length = 64)
    private String city;
    @Column(nullable = false, length = 120)
    private String aboutCompany;
    @Column(nullable = false, length = 80)
    private String address;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Job> jobs = new HashSet<>();

}
