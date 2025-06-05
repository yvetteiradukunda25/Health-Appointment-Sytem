package com.example.healthcaresystem.patient;


import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 50, nullable = false)
    private String firstName;
    @Column(length = 50, nullable = false)
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(length = 10, nullable = false)
    private String phone;
    @Column(nullable = false)
    @Past(message = "Date of birth must be in the past" )
    private LocalDate dateOfBirth;


}
