package com.example.healthcaresystem.appointment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping

public class AppointmentController {
    private final AppointmentService appointmentService;

    @PostMapping("/api/appointment")
    public ResponseEntity<Appointment> createAppointment(@RequestBody AppointmentDto dto) {
        Appointment created = appointmentService.createAppointment(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.getAppointmentById(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id, @RequestBody AppointmentDto dto) {
        return ResponseEntity.ok(appointmentService.updateAppointment(id, dto));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}