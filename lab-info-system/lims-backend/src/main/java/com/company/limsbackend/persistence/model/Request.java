package com.company.limsbackend.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Submission submission;

    @ManyToOne
    private Person sequencer;

    private boolean preparationRequest;
    private boolean bioInfoRequest;
    private String read1Structure;
    private String read2Structure;
    private String bc1Structure;
    private String bc2Structure;
    private int numberOfLanes;
    private String seqPrimer;
    private String comments;


}
