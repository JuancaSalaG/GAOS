package com.gaos.gaos.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gaos.gaos.domain.dao.AuthDTO;
import com.gaos.gaos.domain.dao.UsersDTO;
import com.gaos.gaos.domain.interfaces.AuthRepository;
import com.gaos.gaos.persistence.crud.AuthCrudRepository;
import com.gaos.gaos.persistence.crud.PersonCrudRepository;
import com.gaos.gaos.persistence.crud.RoleCrudRepository;
import com.gaos.gaos.persistence.entity.Auth;
import com.gaos.gaos.persistence.entity.Person;
import com.gaos.gaos.persistence.entity.Role;

@Repository
public class AuthRepositoryImp implements AuthRepository {
    @Autowired
    private AuthCrudRepository authCrudRepository;

    @Autowired
    private PersonCrudRepository personCrudRepository;

    @Autowired
    private RoleCrudRepository roleCrudRepository;

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

    @Override
    public Optional<UsersDTO> getUserByAuth(AuthDTO authDTO) {
        Auth auth = authCrudRepository.findByEmailAndPassword(authDTO.getEmail(), authDTO.getPassword()).orElse(null);
        if (auth == null) {
            return Optional.empty();
        }
        UsersDTO user = new UsersDTO();
        user.setAuth(auth);
        Person person = personCrudRepository.findById(auth.getPerson().getPersonId()).orElse(null);
        user.setPerson(person);
        Role role = roleCrudRepository.findByPersonPersonId(person.getPersonId());
        user.setRole(role);
        return Optional.of(user);
    }

}
