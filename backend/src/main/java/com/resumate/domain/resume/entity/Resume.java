package com.resumate.domain.resume.entity;

import com.resumate.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "resumes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Resume extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // identity 자동 증가
    private Integer id;

    @Column(nullable = false)
    private Integer userId;

    private String image;

    @Column(nullable = false, length = 200)
    private String title;

    private String introduction;
}
