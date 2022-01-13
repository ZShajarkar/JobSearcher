package com.example.demo.services;

import com.example.demo.dto.JobDto;
import com.example.demo.dto.ResumeDto;
import com.example.demo.dto.UserDto;
import com.example.demo.mapper.ResumeMapper;
import com.example.demo.model.Resume;
import com.example.demo.repository.ResumeRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@Service
public class ResumeService {
    private final ResumeRepository resumeRepository;
    private final ResumeMapper resumeMapper;

    @Autowired
    public ResumeService(ResumeRepository resumeRepository, ResumeMapper resumeMapper) {
        this.resumeRepository = resumeRepository;
        this.resumeMapper = resumeMapper;
    }


    public Resume store(String jobId, String userId, MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String fileName = StringUtils.cleanPath(originalFilename);
        JobDto job = new JobDto();
        job.setId(Long.valueOf(jobId));

        UserDto user = new UserDto();
        user.setId(Long.valueOf(userId));
        ResumeDto resume = new ResumeDto(fileName, file.getContentType(), file.getBytes(), job, user);
        return resumeRepository.save(this.resumeMapper.toModel(resume));
    }

    public Resume getFile(String id) {
        return resumeRepository.findById(id).orElse(null);
    }

    public Stream<Resume> getAllFiles() {
        return resumeRepository.findAll().stream();
    }

    public List<ResumeDto> getResumesByJobId(Long jobId) throws NotFoundException {
        List<ResumeDto> resumeDtos = this.resumeMapper.toDto(resumeRepository.findByJob(jobId));
        if (resumeDtos.isEmpty())
            throw new NotFoundException("رزومه ای برای این آگهی  ارسال نشده است");
        return resumeDtos;
    }
}
