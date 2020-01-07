package com.jalon.demo.sys.util;

import com.jalon.demo.sys.dto.UserDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityContextUtils {
    public static UserDTO getSecurityUserInfo() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return (UserDTO) principal;
        } else {
            return null;
        }
    }
}
