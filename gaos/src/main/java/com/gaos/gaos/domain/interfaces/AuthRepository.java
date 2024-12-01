package com.gaos.gaos.domain.interfaces;

import java.util.List;
import java.util.Optional;

import com.gaos.gaos.persistence.entity.Auth;

public interface AuthRepository {
    Auth save(Auth auth);
    List<Auth> getAll();
    Optional<Auth> getById(int id);
    void delete(int id);
}
