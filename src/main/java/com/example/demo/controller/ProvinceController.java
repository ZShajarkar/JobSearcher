package com.example.demo.controller;

import com.example.demo.services.ProvinceService;
import com.example.demo.util.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/public/provinces/v1")
public class ProvinceController {
    private final ProvinceService provinceService;

    @Autowired
    public ProvinceController(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    @GetMapping()
    public ResponseEntity<?> getProvinces() {
        try {
            return ResponseFactory.ok(provinceService.getProvinces());
        } catch (Exception e) {
            return ResponseFactory.handel((HttpClientErrorException) e);
        }
    }
}
