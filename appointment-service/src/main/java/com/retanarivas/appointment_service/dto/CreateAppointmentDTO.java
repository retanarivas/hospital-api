package com.retanarivas.appointment_service.dto;

import com.retanarivas.appointment_service.enums.AppointmentStatus;

import java.time.LocalDate;
import java.time.LocalTime;

public class CreateAppointmentDTO {

    private LocalDate date;

    private LocalTime time;

    private AppointmentStatus appointmentStatus;

    private Long patientId;

    private Long doctorId;

}
