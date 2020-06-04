package com.company.limsbackend.service;

import com.company.limsbackend.persistence.model.DBFile;
import com.company.limsbackend.persistence.model.Person;
import com.company.limsbackend.persistence.model.Submission;
import com.company.limsbackend.persistence.repository.PersonRepository;
import com.company.limsbackend.persistence.repository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubmissionService {
    private final SubmissionRepository submissionRepository;
    private final DBFileStorageService storageService;
    private final PersonRepository personRepository;

    public Submission saveSubmission(Person person, MultipartFile excelFile, MultipartFile pdfFile) {
        DBFile dbExcelFile = storageService.storeFile(excelFile);
        DBFile dbPdfFile = storageService.storeFile(pdfFile);
        Person submitter = toSubmitter(person);

        Submission submission = Submission.builder()
                .submitter(submitter)
                .submissionDate(LocalDateTime.now())
                .excelFile(dbExcelFile)
                .sequencingAuth(dbPdfFile)
                .build();
        return submissionRepository.save(submission);

    }

    private Person toSubmitter(Person person) {
        Optional<Person> optionalPerson = personRepository.findByEmail(person.getEmail());
        if (optionalPerson.isPresent()) {
            return optionalPerson.get();
        } else {
            return personRepository.save(person);
        }
    }

    public Submission getSubmission(Long submissionId) {
        return submissionRepository.findById(submissionId)
                .orElseThrow(() -> new ObjectNotFoundException("submissionId", Submission.class.getName()));
    }
}
