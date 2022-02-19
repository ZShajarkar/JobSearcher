package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(
            "select u from User u where u.email=:email"
    )
    User findByEmail(
            @Param("email") String email
    );

    Boolean existsByEmail(String email);

    @Query(
            "select u from User u inner join Resume resume on u.id=resume.user.id where resume.job.id=:jobId"
    )
   List<User> findByJobId(
            @Param("jobId") Long jobId
    );

}
