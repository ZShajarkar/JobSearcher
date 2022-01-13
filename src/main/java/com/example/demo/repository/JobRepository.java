package com.example.demo.repository;

import com.example.demo.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    @Query(
            "select job from Job job inner join Company company on job.company.id=company.id " +
                    "where (:jobTitle is null or job.JobTitle=:jobTitle) and(:city is null or company.city=:city)")
    List<Job> findByJobTitleAndCity(
            @Param("jobTitle") String jobTitle,
            @Param("city") String city
    );
}
