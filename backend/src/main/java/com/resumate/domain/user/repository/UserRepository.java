package com.resumate.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resumate.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
