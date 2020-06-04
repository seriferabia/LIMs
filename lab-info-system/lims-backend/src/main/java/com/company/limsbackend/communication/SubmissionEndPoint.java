package com.company.limsbackend.communication;

import com.company.limsbackend.payload.SubmissionResponse;
import com.company.limsbackend.persistence.model.Person;
import com.company.limsbackend.persistence.model.Submission;
import com.company.limsbackend.service.SubmissionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/submission")
@RequiredArgsConstructor
public class SubmissionEndPoint {
    private static final Logger logger = LoggerFactory.getLogger(SubmissionEndPoint.class);
    private final SubmissionService submissionService;

    @PostMapping("/submit")
    public SubmissionResponse submit(String person,
                                     @RequestParam("exlFile") MultipartFile excelFile,
                                     @RequestParam("pdfFile") MultipartFile pdfFile) {
        Person submitter = null;
        try {
            submitter = new ObjectMapper().readValue(person, Person.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Submission submission = submissionService.saveSubmission(submitter, excelFile, pdfFile);

        return SubmissionResponse.builder()
                .submissionId(submission.getId())
                .submitterId(submission.getSubmitter().getId())
                .submitterName(submission.getSubmitter().getFirstName())
                .submissionDate(submission.getSubmissionDate())
                .excelFileDownloadUri(getDownloadUri(submission, "excel"))
                .pdfFileDownloadUri(getDownloadUri(submission, "pdf"))
                .build();

    }

    private String getDownloadUri(Submission submission, String fileType) {
        if (fileType.equalsIgnoreCase("pdf")) {
            return ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/downloadFile/")
                    .path(submission.getSequencingAuth().getId().toString())
                    .toUriString();
        } else {
            return ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/downloadFile/")
                    .path(submission.getExcelFile().getId().toString())
                    .toUriString();
        }
    }

}
