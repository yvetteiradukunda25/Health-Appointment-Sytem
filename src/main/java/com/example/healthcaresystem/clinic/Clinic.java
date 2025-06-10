package com.example.healthcaresystem.clinic;

import com.example.healthcaresystem.doctor.Doctor;
import com.example.healthcaresystem.patient.Patient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "Clinic_table")
@AllArgsConstructor
@NoArgsConstructor
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, length = 100, nullable = false)
    private String name;

    @Column( nullable = false)
    private String address;

    @Column(nullable = false,length = 20, unique = true)
    private String phone;

    @OneToMany(mappedBy = "clinic", cascade = CascadeType.ALL)
    private List<Patient> patient;

    @OneToMany(mappedBy = "clinic", cascade = CascadeType.ALL)
    private List<Doctor> doctor ;

}
