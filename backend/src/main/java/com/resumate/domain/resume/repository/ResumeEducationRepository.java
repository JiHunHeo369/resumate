package com.resumate.domain.resume.repository;


import com.resumate.domain.resume.entity.ResumeEducation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeEducationRepository extends JpaRepository<ResumeEducation, Integer> {

    void deleteByResumeId(Integer resumeId);
}
