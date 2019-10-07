package com.minorjava.week3.repository;

import com.minorjava.week3.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonRepository extends CrudRepository<Person, Integer>, PagingAndSortingRepository<Person, Integer> {

}
