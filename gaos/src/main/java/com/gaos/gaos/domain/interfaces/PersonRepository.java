package com.gaos.gaos.domain.interfaces;

import java.util.List;
import java.util.Optional;

import com.gaos.gaos.persistence.entity.Person;

public interface PersonRepository {
    Person save(Person person);
    List<Person> getAll();
    Optional<Person> getById(int id);
    void delete(int id);
}
