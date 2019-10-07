package com.minorjava.week3.service;

import com.minorjava.week3.model.Person;
import com.minorjava.week3.repository.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService implements IPersonService {

    @Autowired
    IPersonRepository personRepository;

    @Override
    public Iterable<Person> findAll(Pageable pageable) {
        return personRepository.findAll();
    }

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public void delete(Integer id) {
        personRepository.deleteById(id);
    }

    @Override
    @Cacheable("Person")
    public Optional<Person> findById(Integer id) {
        return personRepository.findById(id);
    }
}
