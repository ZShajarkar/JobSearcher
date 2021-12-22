package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Company_User_Realation")
public class CompanyUserRelation {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    private String type;

    @Lob
    private byte[] data;

    public CompanyUserRelation() {
    }

    public CompanyUserRelation(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }

}
