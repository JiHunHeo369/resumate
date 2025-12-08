package com.resumate.domain.user.entity;

import java.time.LocalDateTime;

import com.resumate.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // identity 자동 증가
	private Integer id;

	@Column(nullable = false)
	private Integer roleId;

	@Column(nullable = false, length = 255)
	private String loginId;

	@Column(nullable = false, length = 255)
	private String name;

	@Column(length = 255)
	private String image;

	@Column(nullable = false, length = 255)
	private String password;
}