package com.example.healthcaresystem.doctor;

import com.example.healthcaresystem.appointment.Appointment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 50, nullable = false)
    private String firstName;
    @Column(length = 50, nullable = false)
    private String lastName;
    @Column(length = 100, nullable = false)
    private String specialty;
    @Column(unique = true, nullable = false)
    private String email;

//    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
//    private List<Appointment> appointments;
}
