package com.minorjava.week3.controller;

import com.minorjava.week3.model.Person;
import com.minorjava.week3.service.IPersonService;
import com.minorjava.week3.util.FieldErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RestController
public class PersonController {

    private IPersonService personService;

    @Autowired
    public PersonController(IPersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/person")
    public Person create(@Valid @RequestBody Person person){
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
    public Iterable<Person> read() {
        Pageable firstPageWithTwoElements = PageRequest.of(0, 2);
        return personService.findAll(firstPageWithTwoElements);
    }

    @PutMapping("/person")
    public ResponseEntity<Person> update(@RequestBody Person person){
        if(personService.findById(person.getId()).isPresent()){
            return new ResponseEntity(personService.save(person), HttpStatus.OK);
        } else {
            return new ResponseEntity(person, HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/person/{id}")
    public void delete(@PathVariable Integer id) throws ValidationException {
        if(personService.findById(id).isPresent()){
            personService.delete(id);
        } else {
            throw new ValidationException("Person cannot be deleted");
        }
    }
    @GetMapping("/person/{id}")
    public ResponseEntity<Optional<Person>> findById(@PathVariable Integer id){
        CacheControl cacheControl = CacheControl.maxAge(60, TimeUnit.SECONDS);
        var person = personService.findById(id);
        return ResponseEntity.ok()
                .cacheControl(cacheControl)
                .body(person);
        //return personService.findById(id);
    }

}
