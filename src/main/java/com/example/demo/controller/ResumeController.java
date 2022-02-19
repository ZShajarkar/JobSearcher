package com.example.demo.controller;

import com.example.demo.dto.ResumeDto;
import com.example.demo.exception.ExceptionMessage;
import com.example.demo.model.Resume;
import com.example.demo.response.RestResponse;
import com.example.demo.services.ResumeService;
import com.example.demo.util.ResponseFactory;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.ValidationException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/public/resume/v1")
public class ResumeController {

    private final ResumeService storageService;

    @Autowired
    public ResumeController(ResumeService storageService) {
        this.storageService = storageService;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/job/{jobId}")
    public ResponseEntity<?> uploadFile(
            @PathVariable long jobId,
            @RequestHeader("Authorization") String token,
            @RequestParam("file") MultipartFile file) {
        try {
            storageService.store(jobId, file, token);
            return ResponseFactory.ok(ExceptionMessage.RESUME_UPLOADED_SUCCESSFULLY);
        } catch (ValidationException e) {
            return ResponseFactory.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseFactory.badRequest(ExceptionMessage.FILE_COULD_NOT_UPLOAD);
        }
    }

    @GetMapping("/files/job/{job-id}/user-id/{user-id}")
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<byte[]> getFile(@PathVariable("job-id") Long jobId, @PathVariable("user-id") Long userId)  {
        Resume fileDB = storageService.findByJobIdAndUserId(jobId, userId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }

    @GetMapping(value = "/job/{job-id}", produces = "application/json")
    public ResponseEntity<RestResponse<List<ResumeDto>>> getResumesByJobId(@PathVariable("job-id") Long jobId) throws NotFoundException {
        return ResponseFactory.ok(storageService.getResumesByJobId(jobId));
    }


}
