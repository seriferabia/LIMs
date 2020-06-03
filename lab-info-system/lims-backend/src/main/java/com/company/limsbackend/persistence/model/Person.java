package com.company.limsbackend.persistence.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String firstName;

    private String lastName;

    @Email(message = "Email should be valid")
    private String email;

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
