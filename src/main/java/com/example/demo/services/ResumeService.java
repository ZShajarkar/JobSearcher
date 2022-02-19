package com.example.demo.services;

import com.example.demo.dto.ResumeDto;
import com.example.demo.model.Resume;
import javassist.NotFoundException;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.ValidationException;
import java.io.IOException;
import java.util.List;

public interface ResumeService {
    Resume store(long jobId, MultipartFile file, String token) throws IOException, ValidationException;

    List<ResumeDto> getResumesByJobId(Long jobId) throws NotFoundException;

    Resume findByJobIdAndUserId(Long jobId, Long userId);
}
