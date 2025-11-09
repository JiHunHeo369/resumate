package com.resumate.domain.role.entity;


import com.resumate.common.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Role extends BaseEntity {

    @Schema(description = "권한 아이디")
    private Integer id;
    
    @Schema(description = "권한코드")
    private String code;

    @Schema(description = "권한명")
    private String name;

    @Schema(description = "권한에 대한 설명")
    private String description;

}
