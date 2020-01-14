package com.jalon.demo.sys.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class PermissionDTO implements Serializable {

    private String id;
    private String pid;
    private String name;
    private String url;
    private String description;
    private List<PermissionDTO> childs;
}
