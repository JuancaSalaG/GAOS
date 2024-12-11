package com.gaos.gaos.persistence.crud;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.gaos.gaos.persistence.entity.Auth;

public interface AuthCrudRepository extends CrudRepository<Auth, Integer> {
    Optional<Auth> findByEmailAndPassword(String email, String password);
}
