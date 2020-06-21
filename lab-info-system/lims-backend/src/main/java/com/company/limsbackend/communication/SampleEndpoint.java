package com.company.limsbackend.communication;

import com.company.limsbackend.payload.SampleResponse;
import com.company.limsbackend.persistence.model.Sample;
import com.company.limsbackend.service.SampleService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/sample")
@RequiredArgsConstructor
public class SampleEndpoint {
    private static final Logger logger = LoggerFactory.getLogger(SubmissionEndPoint.class);
    private final SampleService sampleService;

    @PostMapping("/submit")
    SampleResponse saveSample(Long requestId, Long preparationPersonId, Long QCPersonId,
                              Long barcode1Id, Long barcode2Id, String sampleData,
                              @RequestParam("imageFile") MultipartFile imageFile) {
        Sample sample = sampleService.saveSample(requestId, preparationPersonId, QCPersonId, barcode1Id, barcode2Id, imageFile, sampleData);
        return sampleService.toSampleResponse(sample);
    }

    @GetMapping("/id/{id}")
    public Sample getSample(@PathVariable Long id) {
        return sampleService.getSample(id);
    }
}
