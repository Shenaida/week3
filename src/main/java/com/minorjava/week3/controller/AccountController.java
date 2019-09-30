package com.minorjava.week3.controller;

import com.minorjava.week3.model.Account;
import com.minorjava.week3.service.IAccountService;
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
public class AccountController {

    @Autowired
    IAccountService accountService;

    @PostMapping("/account")
    ResponseEntity<Account> create(@Valid @RequestBody Account account) throws ValidationException {
        {
            return new ResponseEntity(accountService.save(account), HttpStatus.OK);
        }
    }
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    List<FieldErrorMessage> exceptionHandler(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<FieldErrorMessage> fieldErrorMessages = fieldErrors.stream().map(fieldError -> new FieldErrorMessage(fieldError.getField(), fieldError.getDefaultMessage())).collect(Collectors.toList());
        return fieldErrorMessages;
    }

     @GetMapping("/account")
    Iterable<Account> read() {
        return accountService.findAll();
    }

    @PutMapping("/account")
    ResponseEntity<Account> update(@RequestBody Account account){
        if(accountService.findById(account.getId()).isPresent()){
            return new ResponseEntity(accountService.save(account), HttpStatus.OK);
        } else {
            return new ResponseEntity(account, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/account/{id}")
    void delete(@PathVariable Integer id) throws ValidationException {
        if(accountService.findById(id).isPresent()){
            accountService.delete(id);
        } else {
            throw new ValidationException("Account cannot be deleted");
        }
    }
    @GetMapping("/account/{id}")
    Optional<Account> findById(@PathVariable Integer id){
        return accountService.findById(id);
    }
}
