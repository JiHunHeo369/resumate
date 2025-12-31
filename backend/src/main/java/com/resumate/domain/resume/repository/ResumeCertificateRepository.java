package com.resumate.domain.resume.repository;


import com.resumate.domain.resume.entity.ResumeCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResumeCertificateRepository extends JpaRepository<ResumeCertificate, Integer> {
    void deleteByResumeId(Integer resumeId);
    
    @Query("SELECT rc FROM ResumeCertificate rc WHERE rc.resume.id = :resumeId")
    List<ResumeCertificate> findByResumeId(@Param("resumeId") Integer resumeId);
}
