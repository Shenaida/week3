package com.minorjava.week3.controller;

import com.minorjava.week3.model.Person;
import com.minorjava.week3.service.IPersonService;
import com.minorjava.week3.util.ErrorMessage;
import com.minorjava.week3.util.FieldErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class PersonController {

    @Autowired
    IPersonService personService;

    @PostMapping("/person")
    Person create(@Valid @RequestBody Person person){
            return personService.save(person);
           }
   @ResponseStatus(HttpStatus.BAD_GATEWAY)
   @ExceptionHandler(MethodArgumentNotValidException.class)
   List<FieldErrorMessage> exceptionHandler(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
       List<FieldErrorMessage> fieldErrorMessages = fieldErrors.stream().map(fieldError -> new FieldErrorMessage(fieldError.getField(), fieldError.getDefaultMessage())).collect(Collectors.toList());
    return fieldErrorMessages;
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
    void delete(@PathVariable Integer id) throws ValidationException {
        if(personService.findById(id).isPresent()){
            personService.delete(id);
        } else {
            throw new ValidationException("Person cannot be deleted");
        }
    }
    @GetMapping("/person/{id}")
    Optional<Person> findById(@PathVariable Integer id){
        return personService.findById(id);
    }

}
