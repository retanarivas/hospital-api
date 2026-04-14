package com.retanarivas.appointment_service.services;

import com.retanarivas.appointment_service.dto.AppointmentDTO;
import com.retanarivas.appointment_service.dto.CreateAppointmentDTO;

import java.util.List;

public interface AppointmentService {

    AppointmentDTO findAppointmentById(Long id);
    List<AppointmentDTO> findAppointmentsByDoctorId(Long doctorId);
    AppointmentDTO createAppointment(CreateAppointmentDTO createAppointmentDTO);

}
