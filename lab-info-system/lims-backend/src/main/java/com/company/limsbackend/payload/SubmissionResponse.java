package com.company.limsbackend.payload;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class SubmissionResponse {
    private Long submissionId;
    private Long submitterId;
    private String submitterName;
    private LocalDateTime submissionDate;
    private String excelFileDownloadUri;
    private String pdfFileDownloadUri;
}
