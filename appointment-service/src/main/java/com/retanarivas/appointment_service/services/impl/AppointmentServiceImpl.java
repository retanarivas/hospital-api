package com.retanarivas.appointment_service.services.impl;

import com.retanarivas.appointment_service.clients.DoctorClient;
import com.retanarivas.appointment_service.clients.PatientClient;
import com.retanarivas.appointment_service.dto.AppointmentDTO;
import com.retanarivas.appointment_service.dto.CreateAppointmentDTO;
import com.retanarivas.appointment_service.dto.externalServices.doctorService.DoctorDTO;
import com.retanarivas.appointment_service.dto.externalServices.patientService.PatientDTO;
import com.retanarivas.appointment_service.models.Appointment;
import com.retanarivas.appointment_service.repositories.AppointmentRepository;
import com.retanarivas.appointment_service.services.AppointmentService;
import com.retanarivas.common.exceptions.ResourceNotFoundException;
import com.retanarivas.common.response.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PatientClient patientClient;

    @Autowired
    DoctorClient doctorClient;

    @Override
    public AppointmentDTO findAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .map(appointment -> modelMapper.map(appointment, AppointmentDTO.class))
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));
    }

    @Override
    public AppointmentDTO createAppointment(CreateAppointmentDTO createAppointmentDTO) {
        // Implement the logic to create a new appointment
        // 1- Call patient-service to check if the patient exist (use OpenFeign to make the call)
        ApiResponse<PatientDTO> patientServiceResponse = patientClient.getPatientById(createAppointmentDTO.getPatientId());
        if(!patientServiceResponse.isSuccess()) {
            throw new ResourceNotFoundException("Patient not found with id: " + createAppointmentDTO.getPatientId());
        }

        // 2- Call doctor-service to check if the doctor exist (use OpenFeign to make the call)
        ApiResponse<DoctorDTO> doctorServiceResponse = doctorClient.getDoctorById(createAppointmentDTO.getDoctorId());
        if(!doctorServiceResponse.isSuccess()) {
            throw new ResourceNotFoundException("Doctor not found with id: " + createAppointmentDTO.getDoctorId());
        }

        System.out.println("doctorDTO = " + patientServiceResponse.getData());
        System.out.println("patientDTO = " + doctorServiceResponse.getData());

        // 3- set doctorId and patientId if data exist
        if (patientServiceResponse.getData().getId() == null && doctorServiceResponse.getData().getId() == null) {
            createAppointmentDTO.setPatientId(createAppointmentDTO.getPatientId());
            createAppointmentDTO.setDoctorId(createAppointmentDTO.getDoctorId());
        }

        // 4- If both exist, create the appointment and save it to the database
        Appointment createdAppointment = appointmentRepository.save(modelMapper.map(createAppointmentDTO, Appointment.class));
        AppointmentDTO appointmentDTO = modelMapper.map(createdAppointment, AppointmentDTO.class);

        appointmentDTO.setPatient(patientServiceResponse.getData());
        appointmentDTO.setDoctor(doctorServiceResponse.getData());

        return appointmentDTO;
    }
}
