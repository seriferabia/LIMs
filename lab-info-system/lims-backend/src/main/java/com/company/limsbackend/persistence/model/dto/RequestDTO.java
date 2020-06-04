package com.company.limsbackend.persistence.model.dto;

import lombok.Data;

@Data
public class RequestDTO {
    private boolean preparationRequest;
    private boolean bioInfoRequest;
    private String read1Structure;
    private String read2Structure;
    private String BC1Structure;
    private String BC2Structure;
    private int numberOfLanes;
    private String seqPrimer;
    private String comments;
}
