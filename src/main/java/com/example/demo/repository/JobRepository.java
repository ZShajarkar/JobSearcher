package com.example.demo.repository;

import com.example.demo.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    @Query(
            "select job from Job job inner join Company company on job.company.id=company.id " +
                    "where (:jobTitle is null or job.JobTitle like %:jobTitle% ) and(:city is null or company.city=:city)  ")
    List<Job> findByJobTitleAndCity(
            @Param("jobTitle") String jobTitle,
            @Param("city") String city
    );

    @Query(
            "select job from Job job where job.JobTitle=:jobTitle and job.company.id=:companyId and job.deleted=false ")
    List<Job> findByCompanyAndJobId(@Param("jobTitle") String jobTitle,
                                    @Param("companyId") Long companyId

    );

    @Transactional
    @Modifying
    @Query(
            "UPDATE Job job set job.deleted=true where job.registeredDate=:date")
    void deleteAfterDays(@Param("date") LocalDate days
    );


}
