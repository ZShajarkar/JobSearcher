package com.example.demo.controller;

import com.example.demo.exception.ExceptionMessage;
import com.example.demo.model.Resume;
import com.example.demo.services.ResumeService;
import com.example.demo.util.ResponseFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.ValidationException;

@CrossOrigin
@RestController
@RequestMapping("/public/resume/v1")
@SecurityScheme(name = "Authorization", scheme = "Bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class ResumeController {

    private final ResumeService storageService;

    @Autowired
    public ResumeController(ResumeService storageService) {
        this.storageService = storageService;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/job/{jobId}")
    @Operation(summary = "Users can upload their resume and it needs user authorization",
            tags = "Resume",
            security = @SecurityRequirement(name = "Authorization"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "رزومه با موفقیت ارسال شد"),
                    @ApiResponse(responseCode = "400", description = "فایل اپلود نشد")})

    public ResponseEntity<?> uploadFile(
            @PathVariable long jobId,
            @RequestHeader("Authorization") String token,
            @RequestParam("file") MultipartFile file) {
        try {
            storageService.save(jobId, file, token);
            return ResponseFactory.ok(ExceptionMessage.RESUME_UPLOADED_SUCCESSFULLY);
        } catch (ValidationException e) {
            return ResponseFactory.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseFactory.badRequest(ExceptionMessage.FILE_COULD_NOT_UPLOAD);
        }
    }

    @GetMapping("/files/job/{job-id}/user-id/{user-id}")
    @PreAuthorize("hasRole('COMPANY')")
    @Operation(summary = "Companies can download  resume and it needs company authorization",
            tags = "Resume",
            security = @SecurityRequirement(name = "Authorization"),
            responses = @ApiResponse(responseCode = "200"))
    public ResponseEntity<byte[]> getFile(@PathVariable("job-id") Long jobId, @PathVariable("user-id") Long userId) {
        Resume fileDB = storageService.findByJobIdAndUserId(jobId, userId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }


}
