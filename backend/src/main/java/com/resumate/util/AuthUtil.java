package com.resumate.util;

import com.resumate.common.CustomUserDetail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;

public class AuthUtil {

    // 현재 로그인한 사용자(CustomUserDetails) 반환
    public static CustomUserDetail getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetail) {
            return (CustomUserDetail) authentication.getPrincipal();
        }
        return null;
    }

    // 현재 로그인한 사용자의 userId만 반환
    public static Integer getCurrentUserId() {
        CustomUserDetail userDetails = getCurrentUser();
        return (userDetails != null) ? userDetails.getId() : null;
    }

    // 현재 로그인한 사용자의 권한 반환
    public static String getCurrentUserAuthority() {
        CustomUserDetail userDetails = getCurrentUser();
        return (userDetails != null) ? userDetails.getAuthority() : null;
    }


}
