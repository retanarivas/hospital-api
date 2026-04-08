package com.retanarivas.appointment_service.controllers;

import com.retanarivas.appointment_service.dto.AppointmentDTO;
import com.retanarivas.appointment_service.dto.CreateAppointmentDTO;
import com.retanarivas.appointment_service.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/{id}")
    public AppointmentDTO getAppointmentById(@PathVariable Long id) {
        return appointmentService.findAppointmentById(id);
    }

    @PostMapping
    public AppointmentDTO createAppointment(@RequestBody CreateAppointmentDTO createAppointmentDTO) {
        return appointmentService.createAppointment(createAppointmentDTO);
    }

}
