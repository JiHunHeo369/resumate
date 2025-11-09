package com.resumate.domain.user.entity.custom;

import com.resumate.domain.user.entity.User;
import lombok.Data;

@Data
public class UserRole {
    private User user;

    private String roleCode;
}
