package com.ksh.springbootmultipledatabases.controllers;

import com.ksh.springbootmultipledatabases.entities.h2.Student;
import com.ksh.springbootmultipledatabases.entities.mysql.Person;
import com.ksh.springbootmultipledatabases.entities.postgre.Sequence;
import com.ksh.springbootmultipledatabases.repositories.h2.StudentRepository;
import com.ksh.springbootmultipledatabases.repositories.mysql.PersonRepository;
import com.ksh.springbootmultipledatabases.repositories.postgre.SequenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MultiDatabaseController {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SequenceRepository sequenceRepository;

    @GetMapping("/mysql")
    public List<Person> findAllPerson() {
        return personRepository.findAll();
    }

    @GetMapping("/h2")
    public List<Student> findAllStudent() {
        return studentRepository.findAll();
    }

    @GetMapping("/postgre")
    public List<Sequence> findAllSequence() {
        return sequenceRepository.findAll();
    }
}
