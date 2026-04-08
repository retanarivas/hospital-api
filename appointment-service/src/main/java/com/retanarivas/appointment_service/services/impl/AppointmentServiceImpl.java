package com.retanarivas.appointment_service.services.impl;

import com.retanarivas.appointment_service.dto.AppointmentDTO;
import com.retanarivas.appointment_service.dto.CreateAppointmentDTO;
import com.retanarivas.appointment_service.repositories.AppointmentRepository;
import com.retanarivas.appointment_service.services.AppointmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AppointmentDTO findAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .map(appointment -> modelMapper.map(appointment, AppointmentDTO.class))
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));
    }

    @Override
    public AppointmentDTO createAppointment(CreateAppointmentDTO createAppointmentDTO) {
        // Implement the logic to create a new appointment
        // 1- Call patient-service to check if the doctor and patient exist (use OpenFeign to make the call)
        // 2- Throw the proper exception if either the doctor or patient does not exist
        // 3- If both exist, create the appointment and save it to the database
        return null;
    }
}
