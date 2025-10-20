package com.boilerplate.common.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponseDTO {
    private int status;
    private String code;
    private String message;
}
