package com.retanarivas.appointment_service.controllers;

import com.retanarivas.appointment_service.dto.AppointmentDTO;
import com.retanarivas.appointment_service.dto.CreateAppointmentDTO;
import com.retanarivas.appointment_service.services.AppointmentService;
import com.retanarivas.common.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/{id}")
    public AppointmentDTO getAppointmentById(@PathVariable Long id) {
        return appointmentService.findAppointmentById(id);
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<ApiResponse<List<AppointmentDTO>>> getAppointmentByDoctorId(@PathVariable Long doctorId) {
        return new ResponseEntity<>(
                ApiResponse.success(HttpStatus.OK.value(),
                        "Appointment retrieved successfully",
                        appointmentService.findAppointmentsByDoctorId(doctorId)),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AppointmentDTO>> createAppointment(@RequestBody CreateAppointmentDTO createAppointmentDTO) {
        return new ResponseEntity<>(
                ApiResponse.success(HttpStatus.CREATED.value(),
                        "Appointment created successfully",
                        appointmentService.createAppointment(createAppointmentDTO)),
                HttpStatus.CREATED
        );
    }

}
