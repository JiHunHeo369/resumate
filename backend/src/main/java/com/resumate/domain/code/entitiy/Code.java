package com.resumate.domain.code.entitiy;

import com.resumate.common.entity.BaseEntity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "codes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Code extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // identity 자동 증가
    private Integer id;

    @Column(nullable = false, length = 20)
    private String groupCode;

    @Column(nullable = false, length = 20)
    private String code;

    @Column(nullable = false, length = 50)
    private String name;
}
