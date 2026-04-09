package com.retanarivas.appointment_service.dto.externalServices.doctorService;

import lombok.Data;

@Data
public class DoctorDTO {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String speciality;

}
