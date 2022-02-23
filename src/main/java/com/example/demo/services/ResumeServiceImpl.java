package com.example.demo.services;

import com.example.demo.dto.JobDto;
import com.example.demo.dto.ResumeDto;
import com.example.demo.dto.UserDto;
import com.example.demo.exception.ExceptionMessage;
import com.example.demo.mapper.ResumeMapper;
import com.example.demo.model.Resume;
import com.example.demo.repository.ResumeRepository;
import com.example.demo.validation.ResumeValidation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.ValidationException;
import java.io.IOException;
import java.util.List;
import java.util.Random;

@Service
public class ResumeServiceImpl implements ResumeService {
    private final ResumeRepository resumeRepository;
    private final ResumeMapper resumeMapper;
    private final ResumeValidation resumeValidation;
    private final AuthenticationService authenticationService;
    private static final Random RANDOM = new Random();


    @Autowired
    public ResumeServiceImpl(ResumeRepository resumeRepository, ResumeMapper resumeMapper, ResumeValidation resumeValidation, AuthenticationService authenticationService) {
        this.resumeRepository = resumeRepository;
        this.resumeMapper = resumeMapper;
        this.resumeValidation = resumeValidation;
        this.authenticationService = authenticationService;
    }

    @Override
    public Resume save(long jobId, MultipartFile file, String token) throws IOException, ValidationException {
        String fileName = String.valueOf(System.currentTimeMillis() + RANDOM.nextLong()).concat(".pdf");

        JobDto job = new JobDto();
        job.setId(jobId);

        UserDto user = new UserDto();
        user.setId(authenticationService.getIdOutOfBearerToken(token));
        ResumeDto resume = new ResumeDto(fileName, file.getContentType(), file.getBytes(), job, user);
        resumeValidation.validateSendResume(jobId, file, user.getId());
        return resumeRepository.save(this.resumeMapper.toModel(resume));
    }

    @Override
    public Resume findByJobIdAndUserId(Long jobId, Long userId) {
        return resumeRepository.findByJobIdAndUserId(jobId, userId).orElse(null);
    }

    @Override
    public List<ResumeDto> getResumesByJobId(Long jobId) throws NotFoundException {
        List<ResumeDto> resumeDtos = (List<ResumeDto>) this.resumeMapper.toDto(resumeRepository.findByJob(jobId));
        if (resumeDtos.isEmpty())
            throw new NotFoundException(ExceptionMessage.NO_RESUME_HAS_BEEN_UPLOADED_FOR_THIS_JOB);
        return resumeDtos;
    }


}
