package com.resumate.domain.user.repository;

import com.resumate.domain.user.entity.User;
import com.resumate.domain.user.entity.custom.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value =
            "SELECT u.*, r.code AS role_code " +
            "FROM users u JOIN roles r ON u.role_id = r.id " +
            "WHERE u.login_id = :loginId",
            nativeQuery = true)
    UserRole findUserRoleByLoginId(@Param("loginId") String loginId);
}
