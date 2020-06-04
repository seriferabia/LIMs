package com.company.limsbackend.service;

import com.company.limsbackend.payload.SubmissionResponse;
import com.company.limsbackend.persistence.model.DBFile;
import com.company.limsbackend.persistence.model.Person;
import com.company.limsbackend.persistence.model.Submission;
import com.company.limsbackend.persistence.repository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class SubmissionService {
    private final SubmissionRepository submissionRepository;
    private final DBFileStorageService storageService;
    private final PersonService personService;

    public Submission saveSubmission(String submitterData, MultipartFile excelFile, MultipartFile pdfFile) {
        DBFile dbExcelFile = storageService.storeFile(excelFile);
        DBFile dbPdfFile = storageService.storeFile(pdfFile);
        Person submitter = personService.toPerson(submitterData);

        Submission submission = Submission.builder()
                .submitter(submitter)
                .submissionDate(LocalDateTime.now())
                .excelFile(dbExcelFile)
                .sequencingAuth(dbPdfFile)
                .build();
        return submissionRepository.save(submission);

    }

    public Submission getSubmission(Long submissionId) {
        return submissionRepository.findById(submissionId)
                .orElseThrow(() -> new ObjectNotFoundException("submissionId", Submission.class.getName()));
    }

    public SubmissionResponse toSubmissionResponse(Submission submission) {
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
