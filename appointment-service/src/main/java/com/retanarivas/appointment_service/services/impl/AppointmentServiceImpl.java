package com.retanarivas.appointment_service.services.impl;

import com.retanarivas.appointment_service.clients.doctorService.DoctorClientWrapper;
import com.retanarivas.appointment_service.clients.patientService.PatientClientWrapper;
import com.retanarivas.appointment_service.dto.AppointmentDTO;
import com.retanarivas.appointment_service.dto.CreateAppointmentDTO;
import com.retanarivas.appointment_service.dto.externalServices.doctorService.DoctorDTO;
import com.retanarivas.appointment_service.dto.externalServices.patientService.PatientDTO;
import com.retanarivas.appointment_service.models.Appointment;
import com.retanarivas.appointment_service.repositories.AppointmentRepository;
import com.retanarivas.appointment_service.services.AppointmentService;
import com.retanarivas.common.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PatientClientWrapper patientWrapper;

    @Autowired
    private DoctorClientWrapper doctorWrapper;

    @Override
    public AppointmentDTO findAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .map(appointment -> modelMapper.map(appointment, AppointmentDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + id));
    }

    @Override
    public List<AppointmentDTO> findAppointmentsByDoctorId(Long doctorId) {
        DoctorDTO doctor = doctorWrapper.getDoctorById(doctorId);

        List<Appointment> appointments = appointmentRepository.findAppointmentsByDoctorId(doctor.getId());

        if (appointments.isEmpty()) {
            throw new ResourceNotFoundException("No appointments found for doctor id: " + doctorId);
        }

        return appointments.stream()
                .map(appointment -> {
                    AppointmentDTO appointmentDTO = modelMapper.map(appointment, AppointmentDTO.class);
                    appointmentDTO.setDoctor(doctor);
                    return appointmentDTO;
                })
                .toList();
    }

    @Override
    public AppointmentDTO createAppointment(CreateAppointmentDTO createAppointmentDTO) {
        // Implement the logic to create a new appointment
        // 1- Call patient-service to check if the patient exist (use OpenFeign to make the call)
        PatientDTO patient = patientWrapper.getPatientById(createAppointmentDTO.getPatientId());

        // 2- Call doctor-service to check if the doctor exist (use OpenFeign to make the call)
        DoctorDTO doctor = doctorWrapper.getDoctorById(createAppointmentDTO.getDoctorId());

        // 3- set doctorId and patientId if data exist
        if (patient.getId() == null && doctor.getId() == null) {
            createAppointmentDTO.setPatientId(createAppointmentDTO.getPatientId());
            createAppointmentDTO.setDoctorId(createAppointmentDTO.getDoctorId());
        }

        // 4- If both exist, create the appointment and save it to the database
        Appointment createdAppointment = appointmentRepository.save(modelMapper.map(createAppointmentDTO, Appointment.class));
        AppointmentDTO appointmentDTO = modelMapper.map(createdAppointment, AppointmentDTO.class);

        appointmentDTO.setPatient(patient);
        appointmentDTO.setDoctor(doctor);

        return appointmentDTO;
    }

}
