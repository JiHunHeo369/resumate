package com.boilerplate.common.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PageDTO {

    private int page;

    private int perPage;

    private String sortColumn;

    @Pattern(
            regexp = "asc|desc",
            message = "sortOrder는 asc 또는 desc 허용합니다."
    )
    private String sortOrder;
}
