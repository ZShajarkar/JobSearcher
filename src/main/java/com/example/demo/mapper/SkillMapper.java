package com.example.demo.mapper;

import com.example.demo.dto.SkillDto;
import com.example.demo.model.Skill;

@Mapper
public class SkillMapper implements DtoToModelAndModelToDtoMapper<SkillDto, Skill> {
    @Override
    public Skill toModel(SkillDto dto) {
        if (dto == null) {
            return null;
        }
        Skill skill = new Skill();
        skill.setId(dto.getId());
        skill.setName(dto.getName());
        return skill;
    }

    @Override
    public SkillDto toDto(Skill skill) {
        if (skill == null) {
            return null;
        }
        SkillDto skillDto = new SkillDto();
        skillDto.setName(skill.getName());
        skillDto.setId(skill.getId());

        return skillDto;
    }
}
