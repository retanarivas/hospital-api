package com.retanarivas.doctor_service.services.impl;

import com.retanarivas.doctor_service.dto.DoctorDTO;
import com.retanarivas.doctor_service.exceptions.BadRequestException;
import com.retanarivas.doctor_service.exceptions.ResourceNotFoundException;
import com.retanarivas.doctor_service.models.Doctor;
import com.retanarivas.doctor_service.repositories.DoctorRepository;
import com.retanarivas.doctor_service.services.DoctorService;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<DoctorDTO> getAllDoctors() {
        return doctorRepository.findAll().stream()
                .map(doctor -> modelMapper.map(doctor, DoctorDTO.class))
                .toList();
    }

    @Override
    public DoctorDTO getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .map(doctor -> modelMapper.map(doctor, DoctorDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
    }

    @Override
    public DoctorDTO saveDoctor(DoctorDTO doctorDTO) {
        Doctor doctorEntity = modelMapper.map(doctorDTO, Doctor.class);
        return modelMapper.map(doctorRepository.save(doctorEntity), DoctorDTO.class);
    }

    @Override
    public DoctorDTO updateDoctor(DoctorDTO doctorDTO, Long id) {
        Optional<Doctor> doctorDB = doctorRepository.findById(id);

        if(doctorDB.isPresent()){
            Doctor doctor = doctorDB.get();

            if (doctorDTO.getName() != null)
                doctor.setName(doctorDTO.getName());

            if (doctorDTO.getLastName() != null)
                doctor.setLastName(doctorDTO.getLastName());

            if (doctorDTO.getEmail() != null)
                doctor.setEmail(doctorDTO.getEmail());

            if (doctorDTO.getSpeciality() != null)
                doctor.setSpeciality(doctorDTO.getSpeciality());

            doctorRepository.save(doctor);
            return modelMapper.map(doctor, DoctorDTO.class);
        }
        throw new ResourceNotFoundException("Doctor not found with id: " + id);
    }

    @Override
    public void deleteDoctorById(Long id) {
        if (id == null) {
            throw new BadRequestException("ID cannot be null");
        }

        doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));

        doctorRepository.deleteById(id);
    }
}
