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
@Table(name = "sys_role")
public class Role extends BaseId implements Serializable {
    private static final long serialVersionUID = -5483025972055122929L;
    /**
     * 角色名
     * nullable
     * true:可以为空(默认)
     * false:不能为空
     * unique
     * true:不可重复
     * false:可以重复(默认)
     */
    @Column(nullable = false, unique = true)
    private String name;
    /**
     * 角色code
     * 用于权限控制
     */
    @Column(nullable = false, unique = true)
    private String code;
    //多对多关系映射
    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>(0);

    @ManyToMany(mappedBy = "roles")
    private Set<Permission> permissions = new HashSet<>(0);
}
