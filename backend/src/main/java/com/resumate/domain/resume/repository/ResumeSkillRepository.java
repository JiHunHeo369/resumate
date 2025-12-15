package com.resumate.domain.resume.repository;


import com.resumate.domain.resume.entity.ResumeJob;
import com.resumate.domain.resume.entity.ResumeSkill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeSkillRepository extends JpaRepository<ResumeSkill, Integer> {

    void deleteByResumeId(Integer resumeId);
}
