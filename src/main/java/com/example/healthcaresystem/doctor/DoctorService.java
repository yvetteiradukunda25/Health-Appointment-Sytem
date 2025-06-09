package com.example.healthcaresystem.doctor;

import com.example.healthcaresystem.patient.Patient;
import com.example.healthcaresystem.patient.PatientDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }
    public Doctor CreateDoctor(Doctor doctor) {
       return doctorRepository.save(doctor);
    }

    public List<Doctor> findAllDoctor() {
        return doctorRepository.findAll();
    }
    public Doctor findDoctorById(Long id) {
        return doctorRepository.findById(id).orElseThrow(null);
    }
    public Doctor update(long id, DoctorDto updatedDoctor) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow();new RuntimeException("patient not found");
        doctor.setFirstName(updatedDoctor.getFirstName());
        doctor.setLastName(updatedDoctor.getLastName());
        doctor.setSpecialty(updatedDoctor.getSpecialty());
        doctor.setEmail(updatedDoctor.getEmail());
        return doctorRepository.save(doctor);
    }
    public void deleteDoctor(long id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(null);new RuntimeException("patient not found");
        doctorRepository.delete(doctor);
    }
}
