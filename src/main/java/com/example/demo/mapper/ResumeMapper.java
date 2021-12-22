package com.example.demo.mapper;

import com.example.demo.dto.ResumeDto;
import com.example.demo.model.Resume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResumeMapper implements DtoToModelMapper<ResumeDto, Resume> {
    private final UserMapper userMapper;
    private final JobMapper jobMapper;

    @Autowired
    public ResumeMapper(UserMapper userMapper, JobMapper jobMapper) {
        this.userMapper = userMapper;
        this.jobMapper = jobMapper;
    }

    @Override
    public Resume toModel(ResumeDto dto) {
        if (dto == null) {
            return null;
        }
        Resume resume = new Resume();
        resume.setId(dto.getId());
        resume.setData(dto.getData());
        resume.setJob(this.jobMapper.toModel(dto.getJob()));
        resume.setName(dto.getName());
        resume.setUser(this.userMapper.toModel(dto.getUserDto()));
        resume.setType(dto.getType());
        return resume;
    }

    @Override
    public ResumeDto toDto(Resume vm) {
        if (vm == null) {
            return null;
        }
        ResumeDto resume = new ResumeDto();
        resume.setId(vm.getId());
        resume.setData(vm.getData());
        resume.setJob(this.jobMapper.toDto(vm.getJob()));
        resume.setName(vm.getName());
        resume.setUserDto(this.userMapper.toDto(vm.getUser()));
        resume.setType(vm.getType());
        return resume;
    }
}
