package com.retanarivas.doctor_service.controllers;

import com.retanarivas.common.response.ApiResponse;
import com.retanarivas.doctor_service.dto.DoctorDTO;
import com.retanarivas.doctor_service.services.DoctorService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors")
@Validated
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    ResponseEntity<ApiResponse<List<DoctorDTO>>> getAllDoctors() {
        return ResponseEntity.ok(
                ApiResponse.success(HttpStatus.OK.value(), "Success", doctorService.getAllDoctors())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DoctorDTO>> getDoctorById(
            @PathVariable
            @NotNull(message = "ID is mandatory")
            @Positive(message = "ID cannot be a negative number") Long id) {
        return ResponseEntity.ok(
                ApiResponse.success(HttpStatus.OK.value(), "Success", doctorService.getDoctorById(id))
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<DoctorDTO>> saveDoctor(@Valid @RequestBody DoctorDTO doctorDTO) {
        return new ResponseEntity<>(
                ApiResponse.success(HttpStatus.CREATED.value(), "Doctor created successfully", doctorService.saveDoctor(doctorDTO)),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DoctorDTO>> updateDoctor(
            @Valid @RequestBody DoctorDTO doctorDTO,
            @PathVariable
            @NotNull(message = "ID is mandatory")
            @Positive(message = "ID cannot be a negative number") Long id) {
        return ResponseEntity.ok(
                ApiResponse.success(HttpStatus.OK.value(), "Doctor updated successfully", doctorService.updateDoctor(doctorDTO, id))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<DoctorDTO>> deleteDoctorById(
            @PathVariable
            @NotNull(message = "ID is mandatory")
            @Positive(message = "ID cannot be a negative number") Long id) {
        doctorService.deleteDoctorById(id);
        return ResponseEntity.ok(
                ApiResponse.success(HttpStatus.OK.value(), "Doctor deleted successfully", null)
        );
    }

}
