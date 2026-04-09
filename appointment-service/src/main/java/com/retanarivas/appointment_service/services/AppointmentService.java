package com.retanarivas.appointment_service.services;

import com.retanarivas.appointment_service.dto.AppointmentDTO;
import com.retanarivas.appointment_service.dto.CreateAppointmentDTO;

public interface AppointmentService {

    AppointmentDTO findAppointmentById(Long id);
    AppointmentDTO createAppointment(CreateAppointmentDTO createAppointmentDTO);

}
