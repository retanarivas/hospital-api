package com.retanarivas.appointment_service.dto.externalServices.doctorService;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DoctorDTO {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String speciality;

}
