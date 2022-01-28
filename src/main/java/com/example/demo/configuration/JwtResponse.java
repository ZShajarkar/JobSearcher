package com.example.demo.configuration;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type;
    private Long id;
    private String email;
    private List<String> roles;

    public JwtResponse(String token, Long id, String email, List<String> roles) {
        this.token = token;
        this.id = id;
        this.email = email;
        this.roles = roles;
    }
}
