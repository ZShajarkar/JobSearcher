package com.example.demo.repository;

import com.example.demo.model.Company;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query(
            "select c from Company c where c.companyName=:companyName and c.city=:city"
    )
    Company findByCompanyAndCity(
            @Param("companyName") String companyName,
            @Param("city") int city
    );

    @Query(
            "select c from Company c where c.email=:email"
    )
    Company findByEmail(
            @Param("email") String email
    );
}
