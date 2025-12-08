package com.resumate.domain.resume.repository;


import com.resumate.domain.resume.entity.ResumeCertificate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeCertificateRepository extends JpaRepository<ResumeCertificate, Integer> {
    void deleteByResumeId(Integer resumeId);
}
