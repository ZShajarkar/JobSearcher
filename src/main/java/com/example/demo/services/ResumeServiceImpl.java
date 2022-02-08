package com.example.demo.services;

import com.example.demo.dto.JobDto;
import com.example.demo.dto.ResumeDto;
import com.example.demo.dto.UserDto;
import com.example.demo.mapper.ResumeMapper;
import com.example.demo.model.Resume;
import com.example.demo.repository.ResumeRepository;
import com.example.demo.validation.ResumeValidation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.ValidationException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@Service
public class ResumeServiceImpl implements ResumeService {
    private final ResumeRepository resumeRepository;
    private final ResumeMapper resumeMapper;
    private final ResumeValidation resumeValidation;
    private final AuthenticationService authenticationService;

    @Autowired
    public ResumeServiceImpl(ResumeRepository resumeRepository, ResumeMapper resumeMapper, ResumeValidation resumeValidation, AuthenticationService authenticationService) {
        this.resumeRepository = resumeRepository;
        this.resumeMapper = resumeMapper;
        this.resumeValidation = resumeValidation;
        this.authenticationService = authenticationService;
    }

    @Override
    public Resume store(String jobId, MultipartFile file, String token) throws IOException, ValidationException {
        String originalFilename = file.getOriginalFilename();
        String fileName = StringUtils.cleanPath(originalFilename);
        JobDto job = new JobDto();
        job.setId(Long.valueOf(jobId));

        UserDto user = new UserDto();
        user.setId(Long.valueOf(authenticationService.getIdOutOfBearerToken(token)));
        ResumeDto resume = new ResumeDto(fileName, file.getContentType(), file.getBytes(), job, user);
        resumeValidation.validateFirstSend(Long.valueOf(user.getId()), Long.valueOf(jobId));
        return resumeRepository.save(this.resumeMapper.toModel(resume));
    }

    @Override
    public Resume getFile(String id) {
        return resumeRepository.findById(id).orElse(null);
    }

    @Override
    public Stream<Resume> getAllFiles() {
        return resumeRepository.findAll().stream();
    }

    @Override
    public List<ResumeDto> getResumesByJobId(Long jobId) throws NotFoundException {
        List<ResumeDto> resumeDtos = (List<ResumeDto>) this.resumeMapper.toDto(resumeRepository.findByJob(jobId));
        if (resumeDtos.isEmpty())
            throw new NotFoundException("رزومه ای برای این آگهی  ارسال نشده است");
        return resumeDtos;
    }
}
