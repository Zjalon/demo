package com.jalon.demo.sys.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Builder
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
    @ManyToMany(mappedBy = "users")
    @Cascade(value = {CascadeType.MERGE})
    private Set<Role> roles;
}
