package com.gaos.gaos.domain.interfaces;

import java.util.List;
import java.util.Optional;

import com.gaos.gaos.persistence.entity.Role;

public interface RoleRepository {
    Role save(Role role);
    List<Role> getAll();
    Optional<Role> getById(int id);
    void delete(int id);
}
