package com.company.limsbackend.persistence.repository;

import com.company.limsbackend.persistence.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
