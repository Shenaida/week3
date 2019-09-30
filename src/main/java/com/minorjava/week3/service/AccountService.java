package com.minorjava.week3.service;

import java.util.List;
import java.util.Optional;

import com.minorjava.week3.model.Account;
import com.minorjava.week3.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService{

    @Autowired
    AccountRepository accountRepository;

    @Override
    public List<Account> findAll() {
        return (List<Account>)accountRepository.findAll();
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public void delete(Integer id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Optional<Account> findById(Integer id) {
        return accountRepository.findById(id);
    }

}
