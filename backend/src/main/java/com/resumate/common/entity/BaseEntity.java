package com.resumate.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity {

	protected Integer createdBy;

	protected LocalDateTime createdAt;

	protected Integer updatedBy;

	protected LocalDateTime updatedAt;
}
