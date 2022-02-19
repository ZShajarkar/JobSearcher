package com.example.demo.repository;

import com.example.demo.model.Resume;
import jdk.dynalink.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ResumeRepository extends JpaRepository<Resume, String> {
    @Query(
            "select r from Resume r where r.job.id=:job"
    )
    List<Resume> findByJob(@Param("job") Long job);


    @Query(
            "select r from Resume r where r.job.id=:job AND r.user.id=:user"
    )
    List<Resume> findByJobAndUser(@Param("job") Long job, @Param(("user")) Long user);

    @Query(
            "select r from Resume r where r.job.id=:job AND r.user.id=:user"
    )
    Optional<Resume> findByJobIdAndUserId(@Param("job") Long job, @Param(("user")) Long user);
}

