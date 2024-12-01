package com.gaos.gaos.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaos.gaos.domain.interfaces.RoleRepository;
import com.gaos.gaos.persistence.entity.Role;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public List<Role> getAll() {
        return roleRepository.getAll();
    }

    public Optional<Role> getById(int roleId) {
        return roleRepository.getById(roleId);
    }

    public Optional<Role> updateRole(int roleId, Role role) {
        return roleRepository.getById(roleId).map(roleData -> {
            BeanUtils.copyProperties(roleData, role);
            return roleRepository.save(role);
        });
    }

    public boolean deleteRole(int roleId) {
        return getById(roleId).map(role -> {
            roleRepository.delete(roleId);
            return true;
        }).orElse(false);
    }
}
