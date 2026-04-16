package com.retanarivas.appointment_service.clients.patientService;

import com.retanarivas.appointment_service.dto.externalServices.patientService.PatientDTO;
import com.retanarivas.common.exceptions.ResourceNotFoundException;
import com.retanarivas.common.response.ApiResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class PatientClientWrapper {

    private final PatientClient patientClient;

    @CircuitBreaker(name = "patient-service", fallbackMethod = "getPatientByIdFallback")
    public PatientDTO getPatientById(Long patientId) {
        ApiResponse<PatientDTO> patientServiceResponse = patientClient.getPatientById(patientId);

        if(!patientServiceResponse.isSuccess()) {
            throw new ResourceNotFoundException("Patient not found with id: " + patientId);
        }
        return patientServiceResponse.getData();
    }

    private PatientDTO getPatientByIdFallback(Long patientId, Throwable ex) {
        log.error("Patient Service is unavailable for ID {}. Error: {}", patientId, ex.getMessage());
        return PatientDTO.builder()
                .id(patientId)
                .name("Temporary Unavailable Patient")
                .build();
    }
}
