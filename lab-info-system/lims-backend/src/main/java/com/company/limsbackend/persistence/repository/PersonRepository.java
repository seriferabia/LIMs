package com.company.limsbackend.persistence.repository;

import com.company.limsbackend.persistence.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByEmail(String email);
}
