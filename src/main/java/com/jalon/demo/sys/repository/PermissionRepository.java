package com.jalon.demo.sys.repository;

import com.jalon.demo.sys.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, String>, JpaSpecificationExecutor<Permission> {
    Optional<Permission> findByName(String name);
}
