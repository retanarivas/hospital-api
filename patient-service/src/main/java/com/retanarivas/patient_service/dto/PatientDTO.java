package com.retanarivas.patient_service.dto;

import com.retanarivas.patient_service.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private int age;
    private Gender gender;
    private String phoneNumber;
    private String address;

}
