package com.boilerplate.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boilerplate.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
