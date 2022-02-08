package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Validated
@Table(name = "Company", uniqueConstraints = {@UniqueConstraint(columnNames = {"company_name", "city"})})
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 45,name = "company_name")
    private String companyName;
    @Column(nullable = false, length = 64)
    @Min(1)
    @Max(29)
    private int city;
    @Column(nullable = false, length = 120)
    private String aboutCompany;
    @Column(nullable = false, length = 80)
    private String address;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Job> jobs = new HashSet<>();
    @Column(nullable = false, unique = true, length = 45)
    @NotBlank
    private String email;
    @Column(nullable = false)
    @NotBlank
    @Size(max = 120)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "company_roles",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

}
