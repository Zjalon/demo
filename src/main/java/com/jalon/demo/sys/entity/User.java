package com.jalon.demo.sys.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户实体
 * AllArgsConstructor:所有参数构造方法
 * NoArgsConstructor:无参构造方法
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sys_user")
public class User extends BaseId implements Serializable {

    private static final long serialVersionUID = -4049556761869388274L;
    /**
     * 用户名
     */
    @Column(nullable = false, unique = true)
    private String username;
    /**
     * 密码
     */
    @Column(nullable = false)
    private String password;
    /**
     * 用户是否过期
     * true:不过期
     * false:过期
     */
    @Column(columnDefinition = "bit(1) default true", nullable = false)
    private boolean accountNonExpired = true;
    /**
     * 用户是否锁定
     * true:不锁定
     * false:锁定
     */
    @Column(columnDefinition = "bit(1) default true", nullable = false)
    private boolean accountNonLocked = true;
    /**
     * 用户是否可用
     * true:可用
     * false:不可用
     */
    @Column(columnDefinition = "bit(1) default true", nullable = false)
    private boolean enabled = true;
    /**
     * 用户密码是否过期
     * true:未过期
     * false:过期
     */
    @Column(columnDefinition = "bit(1) default true", nullable = false)
    private boolean credentialsNonExpired = true;
    /**
     * 多对多关系映射
     * Cascade,保存user时,级联保存role
     */
    @ManyToMany
    @JoinTable(name = "user_role_rel", //中间表的名称
            //中间表user_role_rel和当前表关联关系,name指中间表字段名,referencedColumnName指当前实体字段名
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            //中间表user_role_rel和对方表关联关系,name指中间表字段名,referencedColumnName指对方表字段名
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Set<Role> roles = new HashSet<>(0);

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
