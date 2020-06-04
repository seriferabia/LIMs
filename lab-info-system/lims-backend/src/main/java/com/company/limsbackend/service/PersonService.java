package com.company.limsbackend.service;

import com.company.limsbackend.persistence.model.Person;
import com.company.limsbackend.persistence.repository.PersonRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public Person toPerson(String submitterData) {
        Person submitter = null;
        try {
            submitter = new ObjectMapper().readValue(submitterData, Person.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Optional<Person> optionalPerson = personRepository.findByEmail(submitter.getEmail());
        if (optionalPerson.isPresent()) {
            return optionalPerson.get();
        } else {
            return personRepository.save(submitter);
        }
    }
}
