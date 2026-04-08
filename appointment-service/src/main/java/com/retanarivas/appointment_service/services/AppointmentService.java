package com.retanarivas.appointment_service.services;

import com.retanarivas.appointment_service.dto.AppointmentDTO;

public interface AppointmentService {

    AppointmentDTO findAppointmentById(Long id);

}
