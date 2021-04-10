package com.example.demo.Database.Repository;

import com.example.demo.Database.Entity.PermissionEntity;
import com.example.demo.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Integer> {

    PermissionEntity findFirstByPermission(Permission permission);
}