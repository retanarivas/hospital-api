package com.retanarivas.patient_service.controllers;

import com.retanarivas.patient_service.dto.PatientDTO;
import com.retanarivas.patient_service.services.PatientService;
import com.retanarivas.common.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
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
    public ResponseEntity<ApiResponse<PatientDTO>> getPatientById(@PathVariable Long id) {
        return ResponseEntity.ok(
                ApiResponse.success(HttpStatus.OK.value(),
                        "Success",
                        patientService.getPatientById(id))
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PatientDTO>> createPatient(@RequestBody PatientDTO patientDTO) {
        ApiResponse<PatientDTO> response = ApiResponse.success(HttpStatus.CREATED.value(),
                "Patient created successfully",
                patientService.createPatient(patientDTO));

        return new ResponseEntity<>(
                response,
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PatientDTO>> updatePatient(@PathVariable Long id, @RequestBody PatientDTO patientDTO) {
        return ResponseEntity.ok(
                ApiResponse.success(HttpStatus.OK.value(),
                        "Patient updated successfully",
                        patientService.updatePatient(id, patientDTO))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<PatientDTO>> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return new ResponseEntity<>(
                ApiResponse.success(HttpStatus.OK.value(),
                        "Patient deleted successfully",
                        null),
                HttpStatus.OK
        );
    }
}
