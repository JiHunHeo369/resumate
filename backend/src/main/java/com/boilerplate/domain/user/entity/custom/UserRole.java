package com.boilerplate.domain.user.entity.custom;

import com.boilerplate.domain.user.entity.User;
import lombok.Data;

@Data
public class UserRole {
    private User user;

    private String roleCode;
}
