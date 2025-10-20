package com.boilerplate.domain.menu.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "메뉴 엔티티")
public class Menu {

    @Schema(description = "메뉴 아이디")
    private Long id;

    @Schema(description = "메뉴명")
    private String name;

    @Schema(description = "상위 메뉴 아이디")
    private Integer upMenuId;

    @Schema(description = "메뉴 정렬 순서")
    private Integer sortOrder;

    @Schema(description = "경로")
    private String path;

    @Schema(description = "아이콘")
    private String icon;
}
