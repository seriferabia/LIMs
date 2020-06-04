package com.company.limsbackend.service;

import com.company.limsbackend.payload.RequestResponse;
import com.company.limsbackend.persistence.model.Person;
import com.company.limsbackend.persistence.model.Request;
import com.company.limsbackend.persistence.model.Submission;
import com.company.limsbackend.persistence.model.dto.RequestDTO;
import com.company.limsbackend.persistence.repository.RequestRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import org.hibernate.ObjectNotFoundException;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestService {
    private final RequestRepository requestRepository;
    private final SubmissionService submissionService;
    private final PersonService personService;

    public Request saveRequest(Long submissionId, Long personId, String requestData) {
        Submission submission = submissionService.getSubmission(submissionId);
        Person sequencer = personService.getPerson(personId);
        Request request = toRequest(submission, sequencer, requestData);
        return requestRepository.save(request);

    }

    public Request getRequest(Long requestId) {
        return requestRepository.findById(requestId)
                .orElseThrow(() -> new ObjectNotFoundException(requestId, Request.class.getName()));
    }

    public RequestResponse toRequestResponse(Request request) {
        return RequestResponse.builder()
                .requestId(request.getId())
                .submissionId(request.getSubmission().getId())
                .sequencerId(request.getSequencer().getId())
                .build();
    }

    private Request toRequest(Submission submission, Person sequencer, String requestData) {
        RequestDTO requestDTO = null;
        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            requestDTO = objectMapper.readValue(requestData, RequestDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return Request.builder()
                .submission(submission)
                .sequencer(sequencer)
                .preparationRequest(requestDTO.isPreparationRequest())
                .bioInfoRequest(requestDTO.isBioInfoRequest())
                .read1Structure(requestDTO.getRead1Structure())
                .read2Structure(requestDTO.getRead2Structure())
                .bc1Structure(requestDTO.getBc1Structure())
                .bc2Structure(requestDTO.getBc2Structure())
                .numberOfLanes(requestDTO.getNumberOfLanes())
                .seqPrimer(requestDTO.getSeqPrimer())
                .comments(requestDTO.getComments())
                .build();

    }

}
