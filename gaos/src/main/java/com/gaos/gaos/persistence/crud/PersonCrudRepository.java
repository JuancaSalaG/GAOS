package com.gaos.gaos.persistence.crud;

import org.springframework.data.repository.CrudRepository;
import com.gaos.gaos.persistence.entity.Person;

public interface PersonCrudRepository extends CrudRepository<Person, Integer> {
}
