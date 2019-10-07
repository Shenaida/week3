package com.minorjava.week3.service;

import com.minorjava.week3.model.Person;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IPersonService {
    Iterable<Person> findAll(Pageable pageable);
    Person save(Person account);
    void delete(Integer id);
    Optional<Person> findById(Integer id);
}
