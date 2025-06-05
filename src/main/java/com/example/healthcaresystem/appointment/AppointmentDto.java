package com.example.healthcaresystem.appointment;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class AppointmentDto {

    private Long patientId;
    private Long doctorId;
    private LocalDateTime appointmentDate;
    private AppointmentStatus status;
    private String notes;
}