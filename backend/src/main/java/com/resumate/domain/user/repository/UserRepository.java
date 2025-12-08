package com.resumate.domain.user.repository;

import com.resumate.domain.user.entity.User;
import com.resumate.domain.user.entity.custom.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {
}
