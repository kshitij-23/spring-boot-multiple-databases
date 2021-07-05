package com.ksh.springbootmultipledatabases.repositories.postgre;

import com.ksh.springbootmultipledatabases.entities.postgre.Sequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SequenceRepository extends JpaRepository<Sequence, Integer> {
}
