package com.retanarivas.appointment_service.dto.externalServices.patientService;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PatientDTO {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private Integer age;
    private Gender gender;
    private String phoneNumber;
    private String address;
}
