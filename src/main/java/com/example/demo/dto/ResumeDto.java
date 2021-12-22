package com.example.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ResumeDto {
    private String id;
    private String name;
    private String type;
    private byte[] data;
    private JobDto job;
    private UserDto userDto;


    public ResumeDto(String name, String type, byte[] data, JobDto job, UserDto user) {
        this.name = name;
        this.type = type;
        this.data = data;
        this.job = job;
        this.userDto = user;
    }
}
