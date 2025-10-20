package com.boilerplate.domain.code.dto;

import lombok.Data;

public class CodeDTO {


    @Data
    public static class ResponseCode {
        private Integer codeId;
        private String groupCode;
        private String code;
        private String name;
        private String deleted;
    }
}
