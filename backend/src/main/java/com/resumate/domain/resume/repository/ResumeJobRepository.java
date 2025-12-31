package com.resumate.domain.resume.repository;

import com.resumate.domain.resume.entity.ResumeJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResumeJobRepository extends JpaRepository<ResumeJob, Integer> {
    void deleteByResumeId(Integer resumeId);
    
    @Query("SELECT rj FROM ResumeJob rj WHERE rj.resume.id = :resumeId")
    List<ResumeJob> findByResumeId(@Param("resumeId") Integer resumeId);
}
