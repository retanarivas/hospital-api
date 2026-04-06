package com.retanarivas.patient_service.services.impl;

import com.retanarivas.common.exceptions.BadRequestException;
import com.retanarivas.common.exceptions.ResourceNotFoundException;
import com.retanarivas.patient_service.dto.PatientDTO;
import com.retanarivas.patient_service.models.Patient;
import com.retanarivas.patient_service.repositories.PatientRepository;
import com.retanarivas.patient_service.services.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PatientDTO> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(patient -> modelMapper.map(patient, PatientDTO.class))
                .toList();
    }

    @Override
    public PatientDTO getPatientById(Long id) {
        return patientRepository.findById(id)
                .map(patient -> modelMapper.map(patient, PatientDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id " + id));
    }

    @Override
    public PatientDTO createPatient(PatientDTO patientDTO) {
        Patient patientEntity = modelMapper.map(patientDTO, Patient.class);
        return modelMapper.map(patientRepository.save(patientEntity), PatientDTO.class);
    }

    @Override
    public PatientDTO updatePatient(Long id, PatientDTO patientDTO) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);

        if (optionalPatient.isPresent()) {
            Patient patient = optionalPatient.get();

            if (patientDTO.getName() != null)
                patient.setName(patientDTO.getName());

            if (patientDTO.getLastName() != null)
                patient.setLastName(patientDTO.getLastName());

            if (patientDTO.getEmail() != null)
                patient.setEmail(patientDTO.getEmail());

            if (patientDTO.getAge() > 0)
                patient.setAge(patientDTO.getAge());

            if (patientDTO.getGender() != null)
                patient.setGender(patientDTO.getGender());

            if (patientDTO.getPhoneNumber() != null)
                patient.setPhoneNumber(patientDTO.getPhoneNumber());

            if (patientDTO.getAddress() != null)
                patient.setAddress(patientDTO.getAddress());

            return modelMapper.map(patientRepository.save(patient), PatientDTO.class);
        }
        throw new ResourceNotFoundException("Patient not found with id: " + id);
    }

    @Override
    public void deletePatient(Long id) {
        if (id == null)
            throw new BadRequestException("ID cannot be null");
        patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));

        patientRepository.deleteById(id);
    }
}
