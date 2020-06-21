package com.company.limsbackend.persistence.repository;

import com.company.limsbackend.persistence.model.Barcode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarcodeRepository extends JpaRepository<Barcode, Long> {
}
