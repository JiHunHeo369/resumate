package com.resumate.domain.resume.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.resumate.common.exception.CommonApiException;
import com.resumate.common.exception.ErrorCode;
import com.resumate.domain.code.entitiy.QCode;
import com.resumate.domain.resume.dto.ResumeDTO;
import com.resumate.domain.resume.entity.*;
import com.resumate.domain.resume.repository.*;
import com.resumate.domain.user.entity.QUser;
import com.resumate.util.AuthUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResumeService {

        private final JPAQueryFactory queryFactory;
        private final ResumeRepository resumeRepository;
        private final ResumeJobRepository resumeJobRepository;
        private final ResumeSkillRepository resumeSkillRepository;
        private final ResumeCertificateRepository resumeCertificateRepository;
        private final ResumeEducationRepository resumeEducationRepository;
        private final ResumeCareerRepository resumeCareerRepository;

        // 이력서 조회
        public List<ResumeDTO.ResumeListResponse> getResumeList(ResumeDTO.ResumeListRequest request) {
                QResume resume = QResume.resume;
                QUser user = QUser.user;
                QResumeJob resumeJob = QResumeJob.resumeJob;
                QCode code = QCode.code1;

                // TODO request 조건 달기
                List<ResumeDTO.ResumeListResponse> res = queryFactory
                                .select(
                                                resume.id,
                                                resume.userId,
                                                user.name,
                                                resume.image,
                                                resume.title,
                                                resume.introduction,
                                                resumeJob.code,
                                                code.name)
                                .from(resume)
                                .leftJoin(user).on(user.id.eq(resume.userId))
                                .leftJoin(resumeJob).on(resumeJob.resume.id.eq(resume.id))
                                .leftJoin(code).on(code.groupCode.eq("jobs")
                                                .and(code.code.eq(resumeJob.code)))
                                .fetch()
                                .stream()
                                .map(t -> ResumeDTO.ResumeListResponse.builder()
                                                .resumeId(t.get(resume.id))
                                                .userId(t.get(resume.userId))
                                                .userName(t.get(user.name))
                                                .image(t.get(resume.image))
                                                .title(t.get(resume.title))
                                                .introduction(t.get(resume.introduction))
                                                .jobCode(t.get(resumeJob.code))
                                                .jobName(t.get(code.name))
                                                .build())
                                .toList();

                return res;
        }

        // 이력서 생성
        @Transactional
        public Integer createResume(ResumeDTO.ResumeCreateRequest request) {

                // 이력서
                Resume resume = Resume.builder()
                                .introduction(request.getIntroduction())
                                .userId(AuthUtil.getCurrentUserId())
                                .image(request.getImage())
                                .title(request.getTitle())
                                .build();

                // BaseEntity 필드는 @SuperBuilder가 제대로 작동하지 않을 수 있으므로 setter 사용
                resume.setCreatedBy(AuthUtil.getCurrentUserId());
                resume.setCreatedAt(LocalDateTime.now());

                resumeRepository.save(resume);

                // 직업
                Optional.ofNullable(request.getJobs())
                                .orElse(Collections.emptyList())
                                .forEach(item -> {
                                        resumeJobRepository.save(
                                                        ResumeJob.builder()
                                                                        .resume(resume)
                                                                        .code(item)
                                                                        .build());
                                });

                // skill
                Optional.ofNullable(request.getSkills())
                                .orElse(Collections.emptyList())
                                .forEach(item -> {
                                        resumeSkillRepository.save(
                                                        ResumeSkill.builder()
                                                                        .resume(resume)
                                                                        .name(item)
                                                                        .build());
                                });

                // 자격증
                Optional.ofNullable(request.getCertificates())
                                .orElse(Collections.emptyList())
                                .forEach(item -> {
                                        resumeCertificateRepository.save(
                                                        ResumeCertificate.builder()
                                                                        .resume(resume)
                                                                        .name(item)
                                                                        .build());
                                });

                // 교육
                Optional.ofNullable(request.getEducations())
                                .orElse(Collections.emptyList())
                                .forEach(item -> {
                                        resumeEducationRepository.save(
                                                        ResumeEducation.builder()
                                                                        .resume(resume)
                                                                        .entranceAt(item.getEntranceAt())
                                                                        .graduateAt(item.getGraduateAt())
                                                                        .major(item.getMajor())
                                                                        .schoolName(item.getSchoolName())
                                                                        .build());
                                });

                // 경력
                Optional.ofNullable(request.getCareers())
                                .orElse(Collections.emptyList())
                                .forEach(item -> {
                                        resumeCareerRepository.save(
                                                        ResumeCareer.builder()
                                                                        .resume(resume)
                                                                        .endedAt(item.getEndedAt())
                                                                        .startedAt(item.getStartedAt())
                                                                        .job(item.getJob())
                                                                        .companyName(item.getCompanyName())
                                                                        .build());
                                });

                return resume.getId();
        }

        @Transactional
        public Integer updateResume(Integer resumeId, ResumeDTO.ResumeCreateRequest request) {
                // 1. 기존 Resume 조회
                Resume resume = resumeRepository.findById(resumeId)
                                .orElseThrow(() -> new IllegalArgumentException("Resume not found: " + resumeId));

                if (resume == null) {
                        throw new CommonApiException(ErrorCode.RESUME_NOT_FOUND);
                }

                // 2. 기본 정보 업데이트
                resume.setIntroduction(request.getIntroduction());
                resume.setImage(request.getImage());
                resume.setTitle(request.getTitle());
                resume.setUpdatedBy(AuthUtil.getCurrentUserId());
                resume.setUpdatedAt(LocalDateTime.now());

                resumeRepository.save(resume);

                // 3. 기존 하위 엔티티 삭제 후 새로 저장 (간단한 방식)
                resumeJobRepository.deleteByResumeId(resumeId);
                Optional.ofNullable(request.getJobs())
                                .orElse(Collections.emptyList())
                                .forEach(item -> resumeJobRepository.save(
                                                ResumeJob.builder()
                                                                .resume(resume)
                                                                .code(item)
                                                                .build()));

                resumeSkillRepository.deleteByResumeId(resumeId);
                Optional.ofNullable(request.getSkills())
                                .orElse(Collections.emptyList())
                                .forEach(item -> resumeSkillRepository.save(
                                                ResumeSkill.builder()
                                                                .resume(resume)
                                                                .name(item)
                                                                .build()));

                resumeCertificateRepository.deleteByResumeId(resumeId);
                Optional.ofNullable(request.getCertificates())
                                .orElse(Collections.emptyList())
                                .forEach(item -> resumeCertificateRepository.save(
                                                ResumeCertificate.builder()
                                                                .resume(resume)
                                                                .name(item)
                                                                .build()));

                resumeEducationRepository.deleteByResumeId(resumeId);
                Optional.ofNullable(request.getEducations())
                                .orElse(Collections.emptyList())
                                .forEach(item -> resumeEducationRepository.save(
                                                ResumeEducation.builder()
                                                                .resume(resume)
                                                                .entranceAt(item.getEntranceAt())
                                                                .graduateAt(item.getGraduateAt())
                                                                .major(item.getMajor())
                                                                .schoolName(item.getSchoolName())
                                                                .build()));

                resumeCareerRepository.deleteByResumeId(resumeId);
                Optional.ofNullable(request.getCareers())
                                .orElse(Collections.emptyList())
                                .forEach(item -> resumeCareerRepository.save(
                                                ResumeCareer.builder()
                                                                .resume(resume)
                                                                .endedAt(item.getEndedAt())
                                                                .startedAt(item.getStartedAt())
                                                                .job(item.getJob())
                                                                .companyName(item.getCompanyName())
                                                                .build()));

                return resume.getId();
        }

        // 이력서 상세
        public ResumeDTO.ResumeDetailResponse getResume(Integer resumeId) {
                // 1. 이력서 기본 정보 조회
                Resume resume = resumeRepository.findById(resumeId)
                                .orElseThrow(() -> new CommonApiException(ErrorCode.RESUME_NOT_FOUND));

                // 2. 하위 엔티티들 조회
                List<String> jobs = resumeJobRepository.findByResumeId(resumeId)
                                .stream()
                                .map(ResumeJob::getCode)
                                .toList();

                List<String> skills = resumeSkillRepository.findByResumeId(resumeId)
                                .stream()
                                .map(ResumeSkill::getName)
                                .toList();

                List<String> certificates = resumeCertificateRepository.findByResumeId(resumeId)
                                .stream()
                                .map(ResumeCertificate::getName)
                                .toList();

                List<ResumeDTO.ResumeEducationRequest> educations = resumeEducationRepository.findByResumeId(resumeId)
                                .stream()
                                .map(education -> new ResumeDTO.ResumeEducationRequest(
                                                education.getSchoolName(),
                                                education.getEntranceAt(),
                                                education.getGraduateAt(),
                                                education.getMajor()))
                                .toList();

                List<ResumeDTO.ResumeCareerRequest> careers = resumeCareerRepository.findByResumeId(resumeId)
                                .stream()
                                .map(career -> new ResumeDTO.ResumeCareerRequest(
                                                career.getCompanyName(),
                                                career.getJob(),
                                                career.getStartedAt(),
                                                career.getEndedAt()))
                                .toList();

                // 3. Response DTO 생성
                return ResumeDTO.ResumeDetailResponse.builder()
                                .image(resume.getImage())
                                .title(resume.getTitle())
                                .introduction(resume.getIntroduction())
                                .jobs(jobs)
                                .skills(skills)
                                .certificates(certificates)
                                .educations(educations)
                                .careers(careers)
                                .build();
        }
}
