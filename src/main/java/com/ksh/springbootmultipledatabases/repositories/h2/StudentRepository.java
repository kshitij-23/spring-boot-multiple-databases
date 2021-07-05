package com.ksh.springbootmultipledatabases.repositories.h2;

import com.ksh.springbootmultipledatabases.entities.h2.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
