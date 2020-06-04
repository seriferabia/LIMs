package com.company.limsbackend.persistence.model.dto;

import lombok.Data;

@Data
public class RequestDTO {
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
