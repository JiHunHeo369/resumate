package com.resumate.domain.resume.repository;


import com.resumate.domain.resume.entity.ResumeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResumeSkillRepository extends JpaRepository<ResumeSkill, Integer> {

    void deleteByResumeId(Integer resumeId);
    
    @Query("SELECT rs FROM ResumeSkill rs WHERE rs.resume.id = :resumeId")
    List<ResumeSkill> findByResumeId(@Param("resumeId") Integer resumeId);
}
