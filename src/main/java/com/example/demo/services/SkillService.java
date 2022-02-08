package com.example.demo.services;

import com.example.demo.dto.SkillDto;

import java.util.List;

public interface SkillService {
    SkillDto save(SkillDto skillDto);

    List<SkillDto> getSkills();
}
