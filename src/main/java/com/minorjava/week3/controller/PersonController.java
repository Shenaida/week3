package com.minorjava.week3.controller;

import com.minorjava.week3.model.Person;
import com.minorjava.week3.service.IPersonService;
import com.minorjava.week3.util.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.Optional;

@RestController
public class PersonController {

    @Autowired
    IPersonService personService;

    @PostMapping("/person")
    Person create(@RequestBody Person person) throws ValidationException {
        if(person.getId()==0 && person.getFirstName()!=null && person.getLastName()!=null)
        {
            return personService.save(person);
        } else
        {
            throw new ValidationException("Person cannot be created");
        }
    }

    @GetMapping("/person")
    Iterable<Person> read() {
        return personService.findAll();
    }

    @PutMapping("/person")
    ResponseEntity<Person> update(@RequestBody Person person){
        if(personService.findById(person.getId()).isPresent()){
            return new ResponseEntity(personService.save(person), HttpStatus.OK);
        } else {
            return new ResponseEntity(person, HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/person/{id}")
    void delete(@PathVariable Integer id){
        personService.delete(id);
    }
    @GetMapping("/person/{id}")
    Optional<Person> findById(@PathVariable Integer id){
        return personService.findById(id);
    }

}
