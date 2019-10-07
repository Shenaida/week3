package com.minorjava.week3.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private EmployeeStatus position;

    public enum EmployeeStatus{
        MANAGER,
        PROJECTMANAGER,
        TEAMLEADER,
        SECRETARY,
        SALES
    }

}
