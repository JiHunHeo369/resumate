package com.boilerplate.domain.user.mapper;

import com.boilerplate.domain.user.entity.custom.UserRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    UserRole findByLoginId(String loginId);
}