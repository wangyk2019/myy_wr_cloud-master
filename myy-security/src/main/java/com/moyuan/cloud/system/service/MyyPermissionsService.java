package com.moyuan.cloud.system.service;

import com.moyuan.cloud.system.entity.MyyPermissions;
import com.moyuan.cloud.system.repository.MyyPermissionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MyyPermissionsService {

    @Autowired
    private MyyPermissionsRepository repository;

    public MyyPermissions findByRole(String role) {
        Optional<MyyPermissions> opt = repository.findByRole(role);
        return opt.isPresent() ? opt.get() : null;
    }

    public List<MyyPermissions> findAll() {
        return repository.findAll();
    }

}
