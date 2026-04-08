package com.retanarivas.patient_service.controllers;

import com.retanarivas.patient_service.dto.PatientDTO;
import com.retanarivas.patient_service.services.PatientService;
import com.retanarivas.common.response.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
@Validated
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<PatientDTO>>> getAllPatients() {
        return ResponseEntity.ok(
                ApiResponse.success(HttpStatus.OK.value(),
                        "Success",
                        patientService.getAllPatients())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PatientDTO>> getPatientById(
            @PathVariable
            @NotNull(message = "ID is mandatory")
            @Positive(message = "ID cannot be a negative number") Long id) {
        return ResponseEntity.ok(
                ApiResponse.success(HttpStatus.OK.value(),
                        "Success",
                        patientService.getPatientById(id))
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PatientDTO>> createPatient(@Valid @RequestBody PatientDTO patientDTO) {
        ApiResponse<PatientDTO> response = ApiResponse.success(HttpStatus.CREATED.value(),
                "Patient created successfully",
                patientService.createPatient(patientDTO));

        return new ResponseEntity<>(
                response,
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PatientDTO>> updatePatient(
            @PathVariable
            @NotNull(message = "ID is mandatory")
            @Positive(message = "ID cannot be a negative number") Long id,
            @Valid @RequestBody PatientDTO patientDTO) {
        return ResponseEntity.ok(
                ApiResponse.success(HttpStatus.OK.value(),
                        "Patient updated successfully",
                        patientService.updatePatient(id, patientDTO))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<PatientDTO>> deletePatient(
            @PathVariable
            @NotNull(message = "ID is mandatory")
            @Positive(message = "ID cannot be a negative number") Long id) {
        patientService.deletePatient(id);
        return new ResponseEntity<>(
                ApiResponse.success(HttpStatus.OK.value(),
                        "Patient deleted successfully",
                        null),
                HttpStatus.OK
        );
    }
}
