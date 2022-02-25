package com.example.demo.controller;

import com.example.demo.dto.SkillList;
import com.example.demo.services.SkillService;
import com.example.demo.util.ResponseFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "can get skills",
            tags = "Enum data",
            responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = SkillList.class))),
                    @ApiResponse(responseCode = "400", description = "Bad request")})
    public ResponseEntity<?> getSkills() {
        try {
            return ResponseFactory.ok(skillService.getSkills());
        } catch (Exception e) {
            return ResponseFactory.handle((HttpClientErrorException) e);
        }
    }
}
