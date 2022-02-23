package com.example.demo.validation;

import com.example.demo.exception.ExceptionMessage;
import com.example.demo.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.ValidationException;

@Component
public class ResumeValidation {
    private final ResumeRepository resumeRepository;

    @Autowired
    public ResumeValidation(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    public void validateSendResume(long jobId, MultipartFile file, long userId) throws ValidationException {
        validateFirstSend(userId, jobId);
        validateTypeOfFile(file);
    }

    private void validateFirstSend(Long userId, Long jobId) throws ValidationException {
        if (!resumeRepository.findByJobAndUser(jobId, userId).isEmpty())
            throw new ValidationException(ExceptionMessage.YOU_HAVE_BEEN_SENT_RESUME);
    }

    private void validateTypeOfFile(MultipartFile file) throws ValidationException {
        String contentType = file.getContentType();
        if (contentType == null || !contentType.toLowerCase().endsWith("pdf".toLowerCase())) {
            throw new ValidationException(ExceptionMessage.FILE_MUST_BE_IN_PDF);
        }
    }
}
