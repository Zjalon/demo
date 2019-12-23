package com.jalon.demo.sys.service;

import com.jalon.demo.sys.dto.RoleDTO;
import com.jalon.demo.sys.dto.UserDTO;
import com.jalon.demo.sys.entity.Role;
import com.jalon.demo.sys.entity.User;
import com.jalon.demo.sys.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class DemoUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optional = userRepository.findByUsername(username);
        if (!optional.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        User user = optional.get();
        Set<RoleDTO> roleDTOS = new HashSet<>(0);
        for (Role role : user.getRoles()) {
            roleDTOS.add(new RoleDTO(role.getName(), role.getCode()));
        }
        UserDTO userDTO = UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .accountNonExpired(user.isAccountNonExpired())
                .accountNonLocked(user.isAccountNonLocked())
                .credentialsNonExpired(user.isCredentialsNonExpired())
                .enabled(user.isEnabled())
                .authorities(roleDTOS).build();
        return userDTO;
    }
}
