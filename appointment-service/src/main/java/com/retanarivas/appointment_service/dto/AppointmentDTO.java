package com.retanarivas.appointment_service.dto;

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

    private Long patientId;

    private Long doctorId;
}
