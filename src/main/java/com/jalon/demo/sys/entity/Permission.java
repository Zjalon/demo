package com.jalon.demo.sys.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
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

    //多对多关系映射
    @ManyToMany
    @JoinTable(name = "role_permission_rel", //中间表的名称
            //中间表user_role_rel和当前表关联关系,name指中间表字段名,referencedColumnName指当前实体字段名
            joinColumns = {@JoinColumn(name = "permission_id", referencedColumnName = "id")},
            //中间表user_role_rel和对方表关联关系,name指中间表字段名,referencedColumnName指对方表字段名
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Set<Permission> roles = new HashSet<>(0);
}
