package com.ksh.springbootmultipledatabases.repositories.mysql;

import com.ksh.springbootmultipledatabases.entities.mysql.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
}
