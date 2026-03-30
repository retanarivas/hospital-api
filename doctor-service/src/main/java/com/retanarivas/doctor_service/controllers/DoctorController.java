package com.retanarivas.doctor_service.controllers;

import com.retanarivas.doctor_service.models.Doctor;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        Doctor doctor = Doctor.builder()
                .id(id)
                .name("Mayra")
                .lastName("Enriquez")
                .email("enriquez@gmail.com")
                .build();

        return ResponseEntity.ok(doctor);
    }
}
