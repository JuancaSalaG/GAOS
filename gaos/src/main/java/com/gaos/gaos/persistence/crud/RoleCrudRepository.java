package com.gaos.gaos.persistence.crud;

import org.springframework.data.repository.CrudRepository;
import com.gaos.gaos.persistence.entity.Role;

public interface RoleCrudRepository extends CrudRepository<Role, Integer> {
}
