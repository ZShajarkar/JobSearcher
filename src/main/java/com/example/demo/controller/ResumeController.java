package com.example.demo.controller;

import com.example.demo.model.Resume;
import com.example.demo.response.ResponseDb;
import com.example.demo.response.ResponseMessage;
import com.example.demo.services.ResumeService;
import com.example.demo.util.ResponseFactory;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
public class ResumeController {

    private final ResumeService storageService;

    @Autowired
    public ResumeController(ResumeService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/upload/job/{jobId}/user/{userId}")
    public ResponseEntity<?> uploadFile(
            @PathVariable String jobId,
            @PathVariable String userId,
            @RequestParam("file") MultipartFile file) {
        String message;
        try {
            storageService.store(jobId, userId, file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (ValidationException e) {
            return ResponseFactory.badRequest(e.getMessage());
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
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

    @GetMapping("/job/{job-id}")
    public ResponseEntity<?> getResumesByJobId(@PathVariable("job-id") Long jobId) throws NotFoundException {
        return ResponseEntity.ok().body(storageService.getResumesByJobId(jobId));
    }

}
