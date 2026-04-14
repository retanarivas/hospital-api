package com.retanarivas.appointment_service.clients;

import com.retanarivas.appointment_service.dto.externalServices.patientService.PatientDTO;
import com.retanarivas.common.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "patient-service", url = "http://localhost:8002/api/v1/patients")
public interface PatientClient {

    @GetMapping("/{id}")
    ApiResponse<PatientDTO> getPatientById(@PathVariable Long id);

}
