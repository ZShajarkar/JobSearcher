package com.example.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 45)
    private String companyName;
    @Column(nullable = false, length = 64)
    private String city;
    @Column(nullable = false, length = 120)
    private String aboutCompany;
    @Column(nullable = false, length = 80)
    private String address;

}
