package com.example.demo.services;

import com.example.demo.model.CompanyUserRelation;
import com.example.demo.repository.CompanyUserRelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class CompanyUserRelationService {
    private final CompanyUserRelationRepository companyUserRelationRepository;

    @Autowired
    public CompanyUserRelationService(CompanyUserRelationRepository companyUserRelationRepository) {
        this.companyUserRelationRepository = companyUserRelationRepository;
    }

    public CompanyUserRelation store(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String fileName = StringUtils.cleanPath(originalFilename);
        CompanyUserRelation fileDB = new CompanyUserRelation(fileName, file.getContentType(), file.getBytes());
        return companyUserRelationRepository.save(fileDB);
    }

    public CompanyUserRelation getFile(String id) {
        return companyUserRelationRepository.findById(id).orElse(null);
    }

    public Stream<CompanyUserRelation> getAllFiles() {
        return companyUserRelationRepository.findAll().stream();
    }
}
