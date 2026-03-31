package com.retanarivas.doctor_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {

    private Long id;

    private String name;

    private String lastName;

    private String email;

    private String speciality;

}
