package com.company.limsbackend.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SampleResponse {
    private Long sampleId;
    private Long preparationPersonId;
    private Long barcode1Id;
    private Long barcode2Id;

}
