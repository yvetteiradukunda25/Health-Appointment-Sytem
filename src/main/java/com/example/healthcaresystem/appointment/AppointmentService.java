package com.example.healthcaresystem.appointment;

import com.example.healthcaresystem.doctor.DoctorRepository;
import com.example.healthcaresystem.patient.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public Appointment createAppointment(AppointmentDto appointmentDto) {
        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(appointmentDto.getAppointmentDate());
        appointment.setStatus(appointmentDto.getStatus());
        appointment.setNotes(appointmentDto.getNotes());

        appointment.setPatient(
                patientRepository.findById(appointmentDto.getPatientId())
                        .orElseThrow(() -> new IllegalArgumentException("Invalid patient ID"))
        );

        appointment.setDoctor(
                doctorRepository.findById(appointmentDto.getDoctorId())
                        .orElseThrow(() -> new IllegalArgumentException("Invalid doctor ID"))
        );

        return appointmentRepository.save(appointment);
    }

    // Get All
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // Get by ID
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found"));
    }

    // Update
    public Appointment updateAppointment(Long id, AppointmentDto dto) {
        Appointment appointment = getAppointmentById(id);
        appointment.setAppointmentDate(dto.getAppointmentDate());
        appointment.setStatus(dto.getStatus());
        appointment.setNotes(dto.getNotes());
        appointment.setPatient(
                patientRepository.findById(dto.getPatientId())
                        .orElseThrow(() -> new IllegalArgumentException("Invalid patient ID"))
        );
        appointment.setDoctor(
                doctorRepository.findById(dto.getDoctorId())
                        .orElseThrow(() -> new IllegalArgumentException("Invalid doctor ID"))
        );
        return appointmentRepository.save(appointment);
    }

    // Delete
    public void deleteAppointment(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new IllegalArgumentException("Appointment not found");
        }
        appointmentRepository.deleteById(id);
    }

}
