package com.github.naziii.rsp.rockscissorpaper.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * @author Nazila Akbari
 * @version 1.0
 * @since 10/9/18
 */
public class SecurityUtil {

    public static String currentUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername();
    }
}
