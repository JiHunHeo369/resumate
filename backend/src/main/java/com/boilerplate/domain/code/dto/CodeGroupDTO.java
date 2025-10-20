package com.boilerplate.domain.code.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

public class CodeGroupDTO {

    @Data
    public static class ResponseCodeGroup {

        private String groupCode;

        private String groupName;

        private String description;

        private String createdBy;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private String createdAt;

        private String updatedBy;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private String updatedAt;
    }
}
