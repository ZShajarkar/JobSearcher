package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Getter @Setter
@Entity
@Table(name = "Company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, columnDefinition = "nvarchar",length = 45)
    private String companyName;
    @Column(nullable = false, columnDefinition = "nvarchar", length = 64)
    private String city;
    @Column(nullable = false, columnDefinition = "nvarchar", length = 120)
    private String aboutCompany;
    @Column(nullable = false,  columnDefinition = "nvarchar",length = 80)
    private String address;

    @OneToMany(mappedBy="company",cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    private Set<Job> jobs =new HashSet<>();

}
