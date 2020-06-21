package com.company.limsbackend.service;

import com.company.limsbackend.payload.SampleResponse;
import com.company.limsbackend.persistence.model.*;
import com.company.limsbackend.persistence.model.dto.SampleDTO;
import com.company.limsbackend.persistence.repository.SampleRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class SampleService {
    private final SampleRepository sampleRepository;
    private final RequestService requestService;
    private final PersonService personService;
    private final BarcodeService barcodeService;
    private final DBFileStorageService fileStorageService;

    public Sample saveSample(Long requestId, Long preparationPersonId,Long QCPersonId,
                             Long barcode1Id, Long barcode2Id, MultipartFile imageFile,
                             String sampleData) {
        Person preparationPerson = personService.getPerson(preparationPersonId);
        Person QCPerson = personService.getPerson(QCPersonId);
        Barcode barcode1 = barcodeService.getBarcode(barcode1Id);
        Barcode barcode2 = barcodeService.getBarcode(barcode2Id);
        DBFile bioAnalysisImage = fileStorageService.storeFile(imageFile);
        Sample sample = toSample(preparationPerson, QCPerson,
                barcode1, barcode2, bioAnalysisImage, sampleData);
        Request request = requestService.getRequest(requestId);
        sample.getRequests().add(request);
        return sampleRepository.save(sample);

    }

    public Sample getSample(Long sampleId) {
        return sampleRepository.findById(sampleId)
                .orElseThrow(() -> new ObjectNotFoundException(sampleId, Sample.class.getName()));
    }

    public SampleResponse toSampleResponse(Sample sample){
        return SampleResponse.builder()
                .sampleId(sample.getId())
                .preparationPersonId(sample.getPreparationPerson().getId())
                .barcode1Id(sample.getBarcode1().getId())
                .barcode2Id(sample.getBarcode2().getId())
                .build();
    }

    private Sample toSample(Person preparationPerson, Person QCPerson,
                            Barcode barcode1, Barcode barcode2,
                            DBFile bioAnalysisImage, String sampleData) {
        SampleDTO sampleDTO = null;
        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            sampleDTO = objectMapper.readValue(sampleData, SampleDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Sample.builder()
                .tubeLabel(sampleDTO.getTubeLabel())
                .isSpecimenReceived(sampleDTO.isSpecimenReceived())
                .specimenName(sampleDTO.getSpecimenName())
                .specimenOrganism(sampleDTO.getSpecimenOrganism())
                .specimenDescription(sampleDTO.getSpecimenDescription())
                .preparationProtocol(sampleDTO.getPreparationProtocol())
                .preparationDate(sampleDTO.getPreparationDate())
                .preparationPerson(preparationPerson)
                .isPassedQC(sampleDTO.isPassedQC())
                .QCPerson(QCPerson)
                .concentration(sampleDTO.getConcentration())
                .fragmentSize(sampleDTO.getFragmentSize())
                .barcode1(barcode1)
                .barcode2(barcode2)
                .bioAnalysisImage(bioAnalysisImage)
                .build();
    }
}
