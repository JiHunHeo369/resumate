package com.resumate.domain.resume.repository;

import com.resumate.domain.resume.entity.ResumeCareer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResumeCareerRepository  extends JpaRepository<ResumeCareer, Integer> {
    void deleteByResumeId(Integer resumeId);
    
    @Query("SELECT rc FROM ResumeCareer rc WHERE rc.resume.id = :resumeId")
    List<ResumeCareer> findByResumeId(@Param("resumeId") Integer resumeId);
}

