package com.boilerplate.domain.log.entity;

import com.boilerplate.common.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ErrorLog extends BaseEntity {
    private Long id;

    private String exceptionClass;

    private String message;

    private String rootCause;

    private Integer httpStatus;

    private String httpMethod;

    private String path;

    private Integer userId;

    private String clientIp;

    private String userAgent;
}
