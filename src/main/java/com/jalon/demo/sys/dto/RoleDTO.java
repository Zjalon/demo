package com.jalon.demo.sys.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

@Getter
@Setter
public class RoleDTO implements GrantedAuthority, Serializable {
    private static final long serialVersionUID = 460655404210643926L;
    private String id;
    /**
     * 角色名
     */
    private String name;
    /**
     * 角色code
     * 用于权限控制
     */
    private String code;

    @Override
    public String getAuthority() {
        return code;
    }

    public RoleDTO(String id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public RoleDTO(String name, String code) {
        this.name = name;
        this.code = code;
    }
}
