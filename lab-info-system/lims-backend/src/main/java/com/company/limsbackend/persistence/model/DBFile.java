package com.company.limsbackend.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DBFile {

    @Id
    @GeneratedValue
    private Long id;

    private String fileName;
    private String fileType;

    @Lob
    private byte[] data;

}
