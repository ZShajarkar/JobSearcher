package com.example.demo.services;

import com.example.demo.dto.ResumeDto;
import com.example.demo.model.Resume;
import javassist.NotFoundException;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.ValidationException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public interface ResumeService {
    Resume store(String jobId, String userId, MultipartFile file) throws IOException, ValidationException;

    Resume getFile(String id);

    Stream<Resume> getAllFiles();

    List<ResumeDto> getResumesByJobId(Long jobId) throws NotFoundException;
}
