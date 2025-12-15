package com.resumate.domain.resume.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "resume_jobs")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResumeJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id", nullable = false)
    private Resume resume;

    @Column(length = 20)
    private String code;

}
