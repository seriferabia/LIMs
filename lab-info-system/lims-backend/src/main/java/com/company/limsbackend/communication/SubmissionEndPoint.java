package com.company.limsbackend.communication;

import com.company.limsbackend.payload.SubmissionResponse;
import com.company.limsbackend.persistence.model.Submission;
import com.company.limsbackend.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/submission")
@RequiredArgsConstructor
public class SubmissionEndPoint {
    private static final Logger logger = LoggerFactory.getLogger(SubmissionEndPoint.class);
    private final SubmissionService submissionService;

    @PostMapping("/submit")
    public SubmissionResponse submit(String submitterData,
                                     @RequestParam("exlFile") MultipartFile excelFile,
                                     @RequestParam("pdfFile") MultipartFile pdfFile) {

        Submission submission = submissionService.saveSubmission(submitterData, excelFile, pdfFile);
        return submissionService.toSubmissionResponse(submission);

    }

    @GetMapping("/id/{id}")
    public Submission getSubmission(@PathVariable Long id) {
        return submissionService.getSubmission(id);
    }

}
