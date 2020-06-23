package com.company.limsbackend.persistence.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "requests")
public class Sample {

    @Id
    @GeneratedValue
    private Long id;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Request> requests = new ArrayList<>();

    private String tubeLabel;
    private boolean isSpecimenReceived;
    private String specimenName;
    private String specimenOrganism;
    private String specimenDescription;
    private String preparationProtocol;
    private LocalDateTime preparationDate;

    @ManyToOne
    private Person preparationPerson;

    @ManyToOne
    private Barcode barcode1;

    @ManyToOne
    private Barcode barcode2;


    private boolean isPassedQC;

    @ManyToOne
    private Person QCPerson;

    private double concentration;
    private int fragmentSize;

    @OneToOne(fetch = FetchType.EAGER)
    private DBFile bioAnalysisImage;


}
