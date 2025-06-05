package com.example.healthcaresystem.patient;

import org.springframework.stereotype.Service;

import java.util.List;


@Service

public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
    public Patient  CreatePatient(Patient patient) {
        return patientRepository.save(patient);
    }
    public List<Patient> findAllPatient() {
        return patientRepository.findAll();
    }
    public Patient findPatientById(Long id) {
        return patientRepository.findById(id).orElseThrow(null);
    }
    public Patient update(long id, PatientDto updatedPatient) {
        Patient patient = patientRepository.findById(id).orElseThrow();new RuntimeException("User not found");
        patient.setFirstName(updatedPatient.getFirstName());
        patient.setLastName(updatedPatient.getLastName());
        patient.setEmail(updatedPatient.getEmail());
        patient.setPhone(updatedPatient.getPhone());
        patient.setDateOfBirth(updatedPatient.getDateOfBirth());
        return patientRepository.save(patient);
    }
    public void deletePatientById(long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(null);new RuntimeException("User not found");
        patientRepository.deleteById(id);
    }
}