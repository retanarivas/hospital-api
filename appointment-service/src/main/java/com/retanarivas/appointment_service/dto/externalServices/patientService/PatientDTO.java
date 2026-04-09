package com.retanarivas.appointment_service.dto.externalServices.patientService;

import lombok.Data;

@Data
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
