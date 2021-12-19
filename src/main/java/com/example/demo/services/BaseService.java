package com.example.demo.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public final class BaseService {
    public String encode(String simpleText) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(simpleText);
    }
}
