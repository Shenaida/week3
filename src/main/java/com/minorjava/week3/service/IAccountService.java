package com.minorjava.week3.service;

import com.minorjava.week3.model.Account;

import java.util.Optional;

public interface IAccountService {
    Iterable<Account> findAll();
    Account save(Account account);
    void delete(Long id);
    Optional<Account> findById(Long id);
}