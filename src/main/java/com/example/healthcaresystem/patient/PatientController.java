package com.example.healthcaresystem.patient;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/Patients")
@SecurityRequirement(name = "auth")

public class PatientController {
    private final PatientService patientService;

    @PostMapping("/api/create")
    @Operation(summary = "Add a new patient")
    public Patient createPatient( @RequestBody PatientDto patientDto) {
        Patient patient = new Patient();
        patient.setFirstName(patientDto.getFirstName());
        patient.setLastName(patientDto.getLastName());
        patient.setEmail(patientDto.getEmail());
        patient.setPhone(patientDto.getPhone());
        patient.setDateOfBirth(patientDto.getDateOfBirth());
        return patientService.CreatePatient(patient);
    }

    @GetMapping
    @Operation(summary = "Get All Patients")
    public List<Patient> findAllPatient() {
        return patientService.findAllPatient();
    }

    @GetMapping("/api/{id}")
    @Operation(summary = "Find a patient by id")
    public Patient findPatientById(@PathVariable long id) {
        return patientService.findPatientById(id);

    }

    @PutMapping("api/update/{id}")
    @Operation(summary = "Update the Patient")
    public Patient update(@PathVariable long id, @RequestBody PatientDto patientDto) {
        Patient patient = patientService.findPatientById(id);
        patient.setFirstName(patientDto.getFirstName());
        patient.setLastName(patientDto.getLastName());
        patient.setEmail(patientDto.getEmail());
        patient.setPhone(patientDto.getPhone());
        patient.setDateOfBirth(patientDto.getDateOfBirth());
        return patientService.update(id, patientDto);
    }

    @DeleteMapping("/Api/delete/{id}")
    public void deletePatient(@PathVariable long id) {
        patientService.deletePatientById(id);
    }
}

