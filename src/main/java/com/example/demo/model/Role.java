package com.example.demo.model;

import com.example.demo.enums.RoleValue;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RoleValue name;
}