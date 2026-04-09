package com.retanarivas.appointment_service.dto;

import com.retanarivas.appointment_service.dto.externalServices.doctorService.DoctorDTO;
import com.retanarivas.appointment_service.dto.externalServices.patientService.PatientDTO;
import com.retanarivas.appointment_service.enums.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentDTO {

    private Long id;

    private LocalDate date;

    private LocalTime time;

    private AppointmentStatus appointmentStatus;

    private PatientDTO patient;
    private DoctorDTO doctor;
}
