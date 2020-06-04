package com.company.limsbackend.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestResponse {
    private Long requestId;
    private Long submissionId;
    private Long sequencerId;
}
