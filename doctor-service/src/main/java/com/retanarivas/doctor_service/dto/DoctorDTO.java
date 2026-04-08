package com.retanarivas.doctor_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 30, message = "Name must be between 3 and 20 characters")
    private String name;

    @NotBlank(message = "Lastname is mandatory")
    @Size(min = 3, max = 30, message = "Lastname must be between 3 and 20 characters")
    private String lastName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Speciality is mandatory")
    @Size(min = 3, max = 30, message = "Speciality must be between 3 and 40 characters")
    private String speciality;

}
