package com.example.demo.controller;

import com.example.demo.exception.ExceptionMessage;
import com.example.demo.model.Resume;
import com.example.demo.services.ResumeService;
import com.example.demo.util.ResponseFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.ValidationException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    @PostMapping(value = "/job/{jobId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Users can upload their resume and it needs user authorization",
            tags = "Resume",
            security = @SecurityRequirement(name = "Authorization"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "رزومه با موفقیت ارسال شد"),
                    @ApiResponse(responseCode = "400", description = "فایل اپلود نشد")})

    public ResponseEntity<?> uploadFile(
            @PathVariable long jobId,
            @RequestHeader("Authorization") String token,
            @Parameter(
                    description = "File to be uploaded",
                    content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)
            ) @RequestParam("file") MultipartFile file) {
        try {
            storageService.save(jobId, file, token);
            return ResponseFactory.ok(ExceptionMessage.RESUME_UPLOADED_SUCCESSFULLY);
        } catch (ValidationException e) {
            return ResponseFactory.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseFactory.badRequest(ExceptionMessage.FILE_COULD_NOT_UPLOAD);
        }
    }

    @GetMapping(value = "/files/job/{job-id}/user-id/{user-id}")
    @PreAuthorize("hasRole('COMPANY')")
    @Operation(summary = "Companies can download  resume and it needs company authorization",
            tags = "Resume",
            security = @SecurityRequirement(name = "Authorization"),
            responses = @ApiResponse(responseCode = "200"))
    public ResponseEntity<?> getFile(@PathVariable("job-id") Long jobId, @PathVariable("user-id") Long userId) {
        Resume fileDB = storageService.findByJobIdAndUserId(jobId, userId);

        try {
            Path path = Paths.get("/files" + fileDB.getName());
            Files.write(path, fileDB.getData());
            return ResponseEntity.ok().body(path);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }


    }
}
