package com.example.healthcaresystem.patient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    // Create a new patient
    public Patient createPatient(Patient patient) {
        Patient patient1 = new Patient();
        patient1.setFirstName(patient.getFirstName());
        patient1.setLastName(patient.getLastName());
        patient1.setDateOfBirth(patient.getDateOfBirth());
        patient1.setEmail(patient.getEmail());
        return patientRepository.save(patient1);
    }

    // Get all patients
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // Get patient by ID
    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    // Delete patient by ID
    public void deletePatientById(Long id) {
        patientRepository.deleteById(id);
    }
}
