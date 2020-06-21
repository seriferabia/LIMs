package com.company.limsbackend.persistence.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SampleDTO {
    private String tubeLabel;
    private boolean isSpecimenReceived;
    private String specimenName;
    private String specimenOrganism;
    private String specimenDescription;
    private String preparationProtocol;
    private LocalDateTime preparationDate;
    private boolean isPassedQC;
    private double concentration;
    private int fragmentSize;
}
