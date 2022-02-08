package com.example.demo.validation;

import com.example.demo.dto.SkillDto;
import com.example.demo.mapper.SkillMapper;
import com.example.demo.model.Skill;
import com.example.demo.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;

@Component
public class SkillValidation {
    private final SkillRepository skillRepository;
    private final SkillMapper skillMapper;

    @Autowired
    public SkillValidation(SkillRepository skillRepository, SkillMapper skillMapper) {
        this.skillRepository = skillRepository;
        this.skillMapper = skillMapper;
    }


    public void saveSkillIfIdIsNull(Collection<SkillDto> skills) {
        skills.stream().filter(item -> item.getId() == null).forEach(item ->
        {
            if (skillRepository.findByName(this.skillMapper.toModel(item).getName()).get().isEmpty()) {
                Skill savedSkill = saveSkillIfIdIsNull(this.skillMapper.toModel(item));
                item.setId(savedSkill.getId());
            } else {
                item.setId(skillRepository.findByName(this.skillMapper.toModel(item).getName()).get().get(0).getId());
            }
        });
    }

    private Skill saveSkillIfIdIsNull(@Validated Skill skill) {
        Skill returnedSkill = skill;
        if (skill.getId() == null) {
            returnedSkill = this.skillRepository.save(skill);
        }
        return returnedSkill;
    }

}
