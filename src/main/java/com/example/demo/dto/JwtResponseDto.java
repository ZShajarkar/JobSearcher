package com.example.demo.dto;

import com.example.demo.model.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JwtResponseDto {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String email;
    private List<String> roles;
    private String name;

    public JwtResponseDto(String token, Long id, String email, List<String> roles,String name) {
        this.token = token;
        this.id = id;
        this.email = email;
        this.roles = roles;
        this.name=name;
    }
}
