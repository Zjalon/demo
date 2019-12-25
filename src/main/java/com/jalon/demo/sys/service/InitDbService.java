package com.jalon.demo.sys.service;

import com.jalon.demo.sys.entity.Permission;
import com.jalon.demo.sys.entity.Role;
import com.jalon.demo.sys.entity.User;
import com.jalon.demo.sys.repository.PermissionRepository;
import com.jalon.demo.sys.repository.RoleRepository;
import com.jalon.demo.sys.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class InitDbService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    @Value("${demo.init-db}")
    private boolean initDb;

    @Transactional
    public void initDb() {
        if (!initDb) {
            log.info("demo.init-db:false,跳过初始化用户");
            return;
        }
        log.info("初始化用户");
        Optional<User> optionalUser = userRepository.findByUsername("USER");
        User user;
        if (optionalUser.isPresent()) {
            log.info("用户存在");
            user = optionalUser.get();
        } else {
            user = new User("USER", new BCryptPasswordEncoder().encode("123456")); //User.builder().username("USER").password("123456").build();
        }
        Optional<Role> optionalRole = roleRepository.findByCodeAndName("USER", "普通用户");
        Role role;
        if (optionalRole.isPresent()) {
            log.info("角色存在");
            role = optionalRole.get();
        } else {
            role = Role.builder().code("USER").name("普通用户").build();
        }
        if (null == user.getRoles() || user.getRoles().size() < 1) {
            log.info("新建用户角色关系");
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            user.setRoles(roles);
        }
        Optional<Permission> optionalPermission = permissionRepository.findByName("首页");
        Permission permission;
        if (optionalPermission.isPresent()) {
            log.info("功能存在");
            permission = optionalPermission.get();
        } else {
            permission = Permission.builder().name("首页").url("/").build();
        }
        if (null == role.getPermissions() || role.getPermissions().size() < 1) {
            log.info("新建角色功能关系");
            Set<Permission> permissions = new HashSet<>();
            permissions.add(permission);
            role.setPermissions(permissions);
        }
        userRepository.save(user);
        roleRepository.save(role);
        permissionRepository.save(permission);
    }

    public void test() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            log.info(username);
        } else {
            String username = principal.toString();
            log.info(username);
        }
    }
}
