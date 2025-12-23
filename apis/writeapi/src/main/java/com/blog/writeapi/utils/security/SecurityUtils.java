package com.blog.writeapi.utils.security;

import com.blog.writeapi.configs.security.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class SecurityUtils {

    public static Long getUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) return null;

        Object principal = auth.getPrincipal();

        if (principal instanceof UserPrincipal user) {
            return user.getId();
        }

        return null;
    }

    public static String getUserEmail() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) return null;

        Object principal = auth.getPrincipal();

        if (principal instanceof User user) {
            return user.getUsername();
        }

        return null;
    }
}