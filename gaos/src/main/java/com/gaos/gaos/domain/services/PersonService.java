package com.gaos.gaos.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaos.gaos.domain.dao.UsersDTO;
import com.gaos.gaos.domain.interfaces.AuthRepository;
import com.gaos.gaos.domain.interfaces.PersonRepository;
import com.gaos.gaos.domain.interfaces.RoleRepository;
import com.gaos.gaos.persistence.entity.Auth;
import com.gaos.gaos.persistence.entity.Person;
import com.gaos.gaos.persistence.entity.Role;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private RoleRepository roleRepository;

    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    public List<Person> getAll() {
        return personRepository.getAll();
    }

    public Optional<Person> getById(int personId) {
        return personRepository.getById(personId);
    }

    public Optional<Person> updatePerson(int personId, Person person) {
        return personRepository.getById(personId).map(personData -> {
            BeanUtils.copyProperties(person, personData);
            return personRepository.save(person);
        });
    }

    public boolean deletePerson(int personId) {
        return getById(personId).map(person -> {
            personRepository.delete(personId);
            return true;
        }).orElse(false);
    }

    public UsersDTO createUsers(UsersDTO usersDTO) {
        UsersDTO users = new UsersDTO();
        Person person = personRepository.save(usersDTO.getPerson());
        users.setPerson(person);

        Auth auth = usersDTO.getAuth();
        auth.setPerson(person); 
        users.setAuth(authRepository.save(auth));

        Role role = usersDTO.getRole();
        role.setPerson(person);
        users.setRole(roleRepository.save(role));
        return users;
    }
}
