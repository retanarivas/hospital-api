package com.retanarivas.appointment_service.clients.doctorService;

import com.retanarivas.appointment_service.dto.externalServices.doctorService.DoctorDTO;
import com.retanarivas.common.exceptions.ResourceNotFoundException;
import com.retanarivas.common.exceptions.ServiceUnavailableException;
import com.retanarivas.common.response.ApiResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class DoctorClientWrapper {

    private final DoctorClient doctorClient;

    @CircuitBreaker(name = "doctor-service", fallbackMethod = "getDoctorByIdFallback")
    public DoctorDTO getDoctorById(Long doctorId) {
        ApiResponse<DoctorDTO> doctorServiceResponse = doctorClient.getDoctorById(doctorId);

        if(!doctorServiceResponse.isSuccess()) {
            throw new ResourceNotFoundException("Doctor not found with id: " + doctorId);
        }
        return doctorServiceResponse.getData();
    }

    private DoctorDTO getDoctorByIdFallback(Long doctorId, Throwable ex) {
        log.error("Doctor Service is unavailable for ID {}. Error: {}", doctorId, ex.getMessage());
        throw new ServiceUnavailableException("Doctor Service is currently unavailable. Please try again later.");
    }

}
