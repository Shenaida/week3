package com.minorjava.week3.service;

import com.minorjava.week3.model.Person;

import java.util.Optional;

public interface IPersonService {
    Iterable<Person> findAll();
    Person save(Person account);
    void delete(Integer id);
    Optional<Person> findById(Integer id);
}
