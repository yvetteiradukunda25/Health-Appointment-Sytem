package com.example.healthcaresystem.merdicalrecord;


import com.example.healthcaresystem.patient.Patient;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     @Column( nullable = false)
     private String diagnosis;

     @Column( nullable = false)
     private String prescription;

     @Column( nullable = false)
     @Temporal(TemporalType.DATE)
    private LocalDate recordDate;
    private String description;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

}