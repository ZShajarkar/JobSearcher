package com.example.demo.validation;

import com.example.demo.exception.ExceptionMessage;
import com.example.demo.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.ValidationException;

@Component
public class ResumeValidation {
    private final ResumeRepository resumeRepository;

    @Autowired
    public ResumeValidation(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    public void validateFirstSend(Long userId, Long jobId) throws ValidationException {
        if (!resumeRepository.findByJobAndUser(jobId, userId).isEmpty())
            throw new ValidationException(ExceptionMessage.YOU_HAVE_BEEN_SENT_RESUME);
    }
}
