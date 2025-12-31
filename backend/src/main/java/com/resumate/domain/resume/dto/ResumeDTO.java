package com.resumate.domain.resume.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class ResumeDTO {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResumeListRequest {

        @Schema(description = "직무")
        private String jobs;

        @Schema(description = "닉네임")
        private String userName;

        @Schema(description = "타이틀")
        private String title;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ResumeListResponse {
        private Integer resumeId;
        private Integer userId;
        private String userName;
        private String image;
        private String title;
        private String introduction;
        private Integer createdBy;
        private LocalDateTime createdAt;
        private String jobCode;
        private String jobName;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResumeCreateRequest {
        private String image;

        @NotBlank
        private String title;

        @NotBlank
        private String introduction;

        private List<ResumeCareerRequest> careers;

        private List<ResumeEducationRequest> educations;

        private List<String> certificates;

        private List<String> jobs;

        private List<String> skills;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResumeEducationRequest {

        private String schoolName;

        private LocalDateTime entranceAt;

        private LocalDateTime graduateAt;

        private String major;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResumeCareerRequest {

        private String companyName;

        private String job;

        private LocalDateTime startedAt;

        private LocalDateTime endedAt;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ResumeDetailResponse {
        private String image;
        private String title;
        private String introduction;
        private List<ResumeCareerRequest> careers;
        private List<ResumeEducationRequest> educations;
        private List<String> certificates;
        private List<String> jobs;
        private List<String> skills;
    }
}
