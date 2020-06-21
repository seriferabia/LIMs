package com.company.limsbackend.service;

import com.company.limsbackend.persistence.model.Barcode;
import com.company.limsbackend.persistence.repository.BarcodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BarcodeService {
    private final BarcodeRepository barcodeRepository;

    public Barcode saveBarcode(Barcode barcode){
        return barcodeRepository.save(barcode);
    }

    public Barcode getBarcode(Long barcodeId){
        return barcodeRepository.findById(barcodeId).orElseThrow(NoSuchElementException::new);
    }
}
