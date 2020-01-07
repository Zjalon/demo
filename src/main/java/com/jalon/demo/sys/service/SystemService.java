package com.jalon.demo.sys.service;

import com.jalon.demo.sys.dto.RoleDTO;
import com.jalon.demo.sys.dto.UserDTO;
import com.jalon.demo.sys.entity.Role;
import com.jalon.demo.sys.repository.RoleRepository;
import com.jalon.demo.sys.util.SecurityContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SystemService {

    @Autowired
    private RoleRepository roleRepository;

    public void getPermissionList() {
        UserDTO userDTO = SecurityContextUtils.getSecurityUserInfo();
        Set<RoleDTO> roleSet = (Set<RoleDTO>) userDTO.getAuthorities();
        List<String> roleIdList = roleSet.stream().map(RoleDTO::getId).collect(Collectors.toList());
        List<Role> roles = roleRepository.findAllById(roleIdList);
        log.info(roles.toString());
    }
}
