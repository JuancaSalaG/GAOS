package com.gaos.gaos.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.gaos.gaos.domain.dao.UsersDTO;
import com.gaos.gaos.domain.services.PersonService;
import com.gaos.gaos.persistence.entity.Person;


@RestController
@RequestMapping("/persons")
public class PersonController {
    @Autowired
    private PersonService personService;

    @PostMapping("")
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        return new ResponseEntity<>(personService.createPerson(person), HttpStatus.CREATED);
    }
    
    @GetMapping("")
    public ResponseEntity<List<Person>> allPersons() {
        return new ResponseEntity<>(personService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable("id") int personId) {
        return personService.getById(personId)
                .map(person -> new ResponseEntity<>(person, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable("id") int personId, @RequestBody Person newPerson) {
        return personService.updatePerson(personId, newPerson)
                .map(personUpdated -> new ResponseEntity<>(personUpdated, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable("id") int personId) {
        if (personService.deletePerson(personId)) {
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create-users")
    public ResponseEntity<UsersDTO> postMethodName(@RequestBody UsersDTO usersCreation) {
        return new ResponseEntity<>(personService.createUsers(usersCreation), HttpStatus.CREATED);
    }    
}
