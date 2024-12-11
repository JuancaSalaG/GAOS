package com.gaos.gaos.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaos.gaos.domain.dao.AuthDTO;
import com.gaos.gaos.domain.dao.UsersDTO;
import com.gaos.gaos.domain.interfaces.AuthRepository;
import com.gaos.gaos.persistence.entity.Auth;

@Service
public class AuthService {
    @Autowired
    private AuthRepository authRepository;

    public Auth createAuth(Auth auth) {
        return authRepository.save(auth);
    }

    public List<Auth> getAll() {
        return authRepository.getAll();
    }

    public Optional<Auth> getById(int authId) {
        return authRepository.getById(authId);
    }

    public Optional<Auth> updateAuth(int authId, Auth auth) {
        return authRepository.getById(authId).map(authData -> {
            BeanUtils.copyProperties(authData, auth);
            return authRepository.save(auth);
        });
    }

    public Optional<UsersDTO> loginUser(AuthDTO authDTO) {
        return authRepository.getUserByAuth(authDTO);
    }

    public boolean deleteAuth(int authId) {
        return getById(authId).map(auth -> {
            authRepository.delete(authId);
            return true;
        }).orElse(false);
    }
}
