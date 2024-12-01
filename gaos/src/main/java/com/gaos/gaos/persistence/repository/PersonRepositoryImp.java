package com.gaos.gaos.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gaos.gaos.domain.interfaces.PersonRepository;
import com.gaos.gaos.persistence.crud.PersonCrudRepository;
import com.gaos.gaos.persistence.entity.Person;

@Repository
public class PersonRepositoryImp implements PersonRepository {
    @Autowired
    private PersonCrudRepository personCrudRepository;

    @Override
    public Person save(Person person) {
        return personCrudRepository.save(person);
    }

    @Override
    public List<Person> getAll() {
        return (List<Person>) personCrudRepository.findAll();
    }

    @Override
    public Optional<Person> getById(int id) {
        return personCrudRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        personCrudRepository.deleteById(id);
    }
}
