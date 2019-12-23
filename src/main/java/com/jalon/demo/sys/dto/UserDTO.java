package com.jalon.demo.sys.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements UserDetails, Serializable {

    private static final long serialVersionUID = 2409546069729201182L;
    private String id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户是否过期
     * true:不过期
     * false:过期
     */
    private boolean accountNonExpired = true;
    /**
     * 用户是否锁定
     * true:不锁定
     * false:锁定
     */
    private boolean accountNonLocked = true;
    /**
     * 用户密码是否过期
     * true:未过期
     * false:过期
     */
    private boolean credentialsNonExpired = true;
    /**
     * 用户是否可用
     * true:可用
     * false:不可用
     */
    private boolean enabled = true;



    private Set<RoleDTO> authorities = new HashSet<>(0);

    public void setAuthorities(Set<RoleDTO> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
