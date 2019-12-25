package com.jalon.demo.sys.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sys_permission")
public class Permission extends BaseId implements Serializable {
    private static final long serialVersionUID = 5511134276612026126L;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String url;
    private String description;
    private String pid;

    @ManyToMany(targetEntity = Role.class, mappedBy = "permissions")
    private Set<Permission> roles = new HashSet<>(0);
}
