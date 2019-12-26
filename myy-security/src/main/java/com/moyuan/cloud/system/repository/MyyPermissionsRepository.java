package com.moyuan.cloud.system.repository;

import com.moyuan.cloud.system.entity.MyyPermissions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyyPermissionsRepository extends JpaRepository<MyyPermissions, Long> {
    Optional<MyyPermissions> findByRole(String role);

}
