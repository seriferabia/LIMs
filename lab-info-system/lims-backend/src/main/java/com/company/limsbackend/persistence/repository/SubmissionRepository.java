package com.company.limsbackend.persistence.repository;

import com.company.limsbackend.persistence.model.DBFile;
import com.company.limsbackend.persistence.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
}
