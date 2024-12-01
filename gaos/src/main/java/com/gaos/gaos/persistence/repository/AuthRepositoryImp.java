package com.gaos.gaos.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.gaos.gaos.domain.interfaces.AuthRepository;
import com.gaos.gaos.persistence.crud.AuthCrudRepository;
import com.gaos.gaos.persistence.entity.Auth;

@Repository
public class AuthRepositoryImp implements AuthRepository {
    @Autowired
    private AuthCrudRepository authCrudRepository;

    @Override
    public Auth save(Auth auth) {
        return authCrudRepository.save(auth);
    }

    @Override
    public List<Auth> getAll() {
        return (List<Auth>) authCrudRepository.findAll();
    }

    @Override
    public Optional<Auth> getById(int id) {
        return authCrudRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        authCrudRepository.deleteById(id);
    }

}
