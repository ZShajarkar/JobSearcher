package com.example.demo.controller;

import com.example.demo.dto.ResumeDto;
import com.example.demo.exception.ExceptionMessage;
import com.example.demo.model.Resume;
import com.example.demo.response.ResponseDb;
import com.example.demo.response.RestResponse;
import com.example.demo.services.ResumeService;
import com.example.demo.util.ResponseFactory;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

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
            @PathVariable String jobId,
            @RequestHeader("Authorization") String token,
            @RequestParam("file") MultipartFile file) {
        String message;
        try {
            storageService.store(jobId, file,token);
            return ResponseFactory.ok(ExceptionMessage.RESUME_UPLOADED_SUCCESSFULLY);
        } catch (ValidationException e) {
            return ResponseFactory.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseFactory.badRequest(ExceptionMessage.FILE_COULD_NOT_UPLOAD);
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<ResponseDb>> getListFiles() {
        List<ResponseDb> files = storageService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(dbFile.getId())
                    .toUriString();

            return new ResponseDb(
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        Resume fileDB = storageService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }

    @GetMapping(value = "/job/{job-id}",produces = "application/json")
    public ResponseEntity<RestResponse<List<ResumeDto>>> getResumesByJobId(@PathVariable("job-id") Long jobId) throws NotFoundException {
        return ResponseFactory.ok(storageService.getResumesByJobId(jobId));
    }

}
