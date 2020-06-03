package com.company.limsbackend.persistence.repository;

import com.company.limsbackend.persistence.model.DBFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DBFileRepository extends JpaRepository<DBFile, Long> {
}
