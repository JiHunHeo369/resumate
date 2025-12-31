package com.resumate.domain.resume.repository;


import com.resumate.domain.resume.entity.ResumeEducation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResumeEducationRepository extends JpaRepository<ResumeEducation, Integer> {

    void deleteByResumeId(Integer resumeId);
    
    @Query("SELECT re FROM ResumeEducation re WHERE re.resume.id = :resumeId")
    List<ResumeEducation> findByResumeId(@Param("resumeId") Integer resumeId);
}
