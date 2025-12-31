package com.resumate.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity {

	@Column(name = "created_by", nullable = false)
	protected Integer createdBy;

	@Column(name = "created_at", nullable = false)
	protected LocalDateTime createdAt;

	@Column(name = "updated_by")
	protected Integer updatedBy;

	@Column(name = "updated_at")
	protected LocalDateTime updatedAt;
}
