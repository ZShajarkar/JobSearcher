package com.example.demo.model;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(nullable = false, unique = true, length = 45)
    @NotBlank
    private String email;
    @Column(nullable = false, length = 64)
    @NotBlank
    private String password;
    @Column(nullable = false, length = 20)
    @NotBlank
    private String firstName;
    @Column(nullable = false, length = 20)
    @NotBlank
    private String lastName;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Resume> resumes = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
