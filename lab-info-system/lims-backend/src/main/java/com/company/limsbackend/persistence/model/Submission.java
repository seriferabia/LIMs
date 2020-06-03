package com.company.limsbackend.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Submission {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Person submitter;

    @OneToOne(fetch = FetchType.EAGER)
    private DBFile excelFile;

    @OneToOne(fetch = FetchType.EAGER)
    private DBFile sequencingAuth;

    private LocalDateTime submissionDate;

}
