package com.jalon.demo.sys.service;

import com.jalon.demo.sys.dto.PermissionDTO;
import com.jalon.demo.sys.dto.RoleDTO;
import com.jalon.demo.sys.dto.UserDTO;
import com.jalon.demo.sys.entity.Role;
import com.jalon.demo.sys.repository.RoleRepository;
import com.jalon.demo.sys.util.SecurityContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SystemService {

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public List<PermissionDTO> getPermissionList() {
        // 获取用户信息
        UserDTO userDTO = SecurityContextUtils.getSecurityUserInfo();
        // 获取用户权限(角色)
        Set<RoleDTO> roleSet = (Set<RoleDTO>) userDTO.getAuthorities();
        List<String> roleIdList = roleSet.stream().map(RoleDTO::getId).collect(Collectors.toList());
        // 查询角色
        List<Role> roles = roleRepository.findAllById(roleIdList);
        // 遍历角色权限
        List<PermissionDTO> permissionDTOList = new ArrayList<>();
        for (Role role : roles) {
            List<PermissionDTO> tmp = role.getPermissions().stream().map(permission -> {
                PermissionDTO dto = new PermissionDTO();
                dto.setId(permission.getId());
                dto.setUrl(permission.getUrl());
                dto.setName(permission.getName());
                dto.setDescription(permission.getDescription());
                dto.setPid(permission.getPid());
                dto.setChilds(null);
                return dto;
            }).collect(Collectors.toList());
            permissionDTOList.addAll(tmp);
        }
        List<PermissionDTO> result = new ArrayList<>();
        for (PermissionDTO pDTO : permissionDTOList) {
            if (pDTO.getPid() == null) {
                result.add(pDTO);
            }
            for (PermissionDTO cDTO : permissionDTOList) {
                if (pDTO.getId().equals(cDTO.getPid())) {
                    if (pDTO.getChilds() == null) {
                        pDTO.setChilds(new ArrayList<>());
                    }
                    pDTO.getChilds().add(cDTO);
                }
            }
        }
        return result;
    }
}
