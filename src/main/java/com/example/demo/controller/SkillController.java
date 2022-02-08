package com.example.demo.controller;

import com.example.demo.services.SkillService;
import com.example.demo.util.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@CrossOrigin
@RestController
@RequestMapping("/public/skill/v1")
public class SkillController {
    private final SkillService skillService;

    @Autowired
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping()
    public ResponseEntity<?> getSkills() {
        try {
            return ResponseFactory.ok(skillService.getSkills());
        } catch (Exception e) {
            return ResponseFactory.handle((HttpClientErrorException) e);
        }
    }
}
