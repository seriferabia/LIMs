package com.company.limsbackend.persistence.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class BarcodeKit {
    @Id
    @GeneratedValue
    private Long id;
    private String kitProvider;
    private String kitName;
}
