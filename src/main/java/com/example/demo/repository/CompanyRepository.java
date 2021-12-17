package com.example.demo.repository;

import com.example.demo.model.Company;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
