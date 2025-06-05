package com.example.healthcaresystem.merdicalrecord;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import java.time.LocalDate;

@Data
public class CreateMedicalRecordDto {
    private Long patientId;
    private Long doctorId;

    @Column( nullable = false)
    private String diagnosis;

    @Column( nullable = false)
    private String prescription;

    @Column( nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate recordDate;
    private String description;
}