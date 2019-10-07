package com.minorjava.week3.model;

import com.minorjava.week3.controller.AccountController;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.ValidationException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountTests {

    @Autowired
    private AccountController accountController;

    @Test
    public void contextLoads() {
        Assert.assertNotNull(accountController);
    }

    @Test
        public void testCreate() throws ValidationException {
        Account account = new Account ( "INGB1", 100, "active");
        accountController.create(account);
        assertEquals(1, account.getId());
        assertEquals("INGB1", account.getIban());
        assertEquals(100, account.getBalance());
        assertEquals("active", account.getStatus());
    }

    @Test
    public void testDelete() throws ValidationException {
        Account account = new Account ( "INGB1", 100, "active");
        accountController.create(account);
        accountController.delete(account.getId());
        Assertions.assertThat(accountController.findById(account.getId())).isEmpty();

    }
}

