package com.company.limsbackend.persistence.repository;

import com.company.limsbackend.persistence.model.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleRepository extends JpaRepository<Sample, Long> {
}
