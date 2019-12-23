package com.jalon.demo.sys.service;

import com.jalon.demo.sys.entity.Role;
import com.jalon.demo.sys.entity.User;
import com.jalon.demo.sys.repository.RoleRepository;
import com.jalon.demo.sys.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class DemoService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

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
            user = User.builder().username("USER").password("123456").build();
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
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            user.setRoles(roles);
        }
        userRepository.save(user);
        roleRepository.save(role);
    }
}
