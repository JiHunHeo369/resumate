package com.resumate.domain.resume.repository;

import com.resumate.domain.resume.entity.ResumeCareer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeCareerRepository  extends JpaRepository<ResumeCareer, Integer> {
    void deleteByResumeId(Integer resumeId);
}

