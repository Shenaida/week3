package com.minorjava.week3.controller;

import com.minorjava.week3.model.Account;
import com.minorjava.week3.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.Optional;

@RestController
public class AccountController {

    @Autowired
    IAccountService accountService;

    @PostMapping("/account")
    Account create(@RequestBody Account account) throws ValidationException {
        if(account.getId()==0 && account.getIban()!=null && account.getStatus()!=null && account.getBalance()<0)
        {
            return accountService.save(account);
        } else
        {
            throw new ValidationException("Account cannot be created");
        }
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
    void delete(@PathVariable Integer id){
        accountService.delete(id);
    }
    @GetMapping("/account/{id}")
    Optional<Account> findById(@PathVariable Integer id){
        return accountService.findById(id);
    }
}
