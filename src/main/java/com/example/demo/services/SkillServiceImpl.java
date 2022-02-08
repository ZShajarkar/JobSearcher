package com.example.demo.services;

import com.example.demo.dto.SkillDto;
import com.example.demo.mapper.SkillMapper;
import com.example.demo.model.Skill;
import com.example.demo.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {
    private final SkillRepository skillRepository;
    private final SkillMapper skillMapper;

    @Autowired
    public SkillServiceImpl(SkillRepository skillRepository, SkillMapper skillMapper) {
        this.skillRepository = skillRepository;
        this.skillMapper = skillMapper;
    }

    @Override
    public SkillDto save(SkillDto skillDto) {
        Skill skill = this.skillMapper.toModel(skillDto);
        Skill savedSkill = this.skillRepository.save(skill);
        return this.skillMapper.toDto(savedSkill);
    }

    @Override
    public List<SkillDto> getSkills() {
        List<Skill> allSkills = this.skillRepository.findAll();
        return (List<SkillDto>) this.skillMapper.toDto(allSkills);
    }
}
