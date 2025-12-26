package com.resumate.domain.code.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class CodeDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CodeListResponse {
        @Schema(description = "코드 ID")
        private Integer id;

        @Schema(description = "그룹 코드")
        private String groupCode;

        @Schema(description = "코드")
        private String code;

        @Schema(description = "코드명")
        private String name;

        @Schema(description = "생성자")
        private Integer createdBy;

        @Schema(description = "생성일시")
        private LocalDateTime createdAt;

        @Schema(description = "수정자")
        private Integer updatedBy;

        @Schema(description = "수정일시")
        private LocalDateTime updatedAt;
    }
}

