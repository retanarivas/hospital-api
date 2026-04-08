package com.retanarivas.patient_service.dto;

import com.retanarivas.patient_service.enums.Gender;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 30, message = "Name must be between 3 and 20 characters")
    private String name;

    @NotBlank(message = "Lastname is mandatory")
    @Size(min = 3, max = 30, message = "Lastname must be between 3 and 20 characters")
    private String lastName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @Min(value = 0, message = "Age is mandatory and must be between 0 and 120")
    @Max(value = 120, message = "Age is mandatory and must be between 0 and 120")
    @NotNull(message = "Age is mandatory")
    private Integer age;

    private Gender gender;

    @NotBlank(message = "Phone number is mandatory")
    @Pattern(regexp = "^[0-9]+$", message = "Phone number must contain only numbers")
    private String phoneNumber;

    @Size(max = 120, message = "Address must be between 0 and 120 characters")
    private String address;

}
