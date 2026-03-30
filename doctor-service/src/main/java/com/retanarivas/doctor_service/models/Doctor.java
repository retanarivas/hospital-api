package com.retanarivas.doctor_service.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Doctor {

    private Long id;

    private String name;

    private String lastName;

    private String email;

    private String speciality;

}
