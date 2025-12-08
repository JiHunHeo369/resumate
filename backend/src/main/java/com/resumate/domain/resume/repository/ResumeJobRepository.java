package com.resumate.domain.resume.repository;


import com.resumate.domain.resume.entity.Resume;
import com.resumate.domain.resume.entity.ResumeJob;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeJobRepository extends JpaRepository<ResumeJob, Integer> {
    void deleteByResumeId(Integer resumeId);
}
