package com.retanarivas.appointment_service.clients.doctorService;

import com.retanarivas.appointment_service.dto.externalServices.doctorService.DoctorDTO;
import com.retanarivas.common.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "doctor-service", url = "http://localhost:8001/api/v1/doctors")
public interface DoctorClient {

    @GetMapping("/{id}")
    ApiResponse<DoctorDTO> getDoctorById(@PathVariable Long id);
}
