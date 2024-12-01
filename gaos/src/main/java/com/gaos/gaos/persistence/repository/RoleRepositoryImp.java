package com.gaos.gaos.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.gaos.gaos.domain.interfaces.RoleRepository;
import com.gaos.gaos.persistence.crud.RoleCrudRepository;
import com.gaos.gaos.persistence.entity.Role;

@Repository
public class RoleRepositoryImp implements RoleRepository {
    @Autowired
    private RoleCrudRepository roleCrudRepository;

    @Override
    public Role save(Role role) {
        return roleCrudRepository.save(role);
    }

    @Override
    public List<Role> getAll() {
        return (List<Role>) roleCrudRepository.findAll();
    }

    @Override
    public Optional<Role> getById(int id) {
        return roleCrudRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        roleCrudRepository.deleteById(id);
    }
}
