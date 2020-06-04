package com.company.limsbackend.communication;

import com.company.limsbackend.payload.RequestResponse;
import com.company.limsbackend.persistence.model.Request;
import com.company.limsbackend.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/request")
@RequiredArgsConstructor
public class RequestEndpoint {
    private final RequestService requestService;

    @PostMapping("/submit")
    public RequestResponse saveRequest(Long submissionId, Long personId, String requestData) {
        Request request = requestService.saveRequest(submissionId, personId, requestData);
        return requestService.toRequestResponse(request);

    }

    @GetMapping("/id/{requestId}")
    public Request getRequest(@PathVariable Long requestId) {
        return requestService.getRequest(requestId);
    }
}
