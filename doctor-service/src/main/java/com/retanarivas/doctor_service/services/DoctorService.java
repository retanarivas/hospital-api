package com.retanarivas.doctor_service.services;

import com.retanarivas.doctor_service.dto.DoctorDTO;

import java.util.List;

public interface DoctorService {

     List<DoctorDTO> getAllDoctors();
     DoctorDTO getDoctorById(Long id);
     DoctorDTO saveDoctor(DoctorDTO doctorDTO);
     DoctorDTO updateDoctor(DoctorDTO doctorDTO, Long id);
     void deleteDoctorById(Long id);

}
