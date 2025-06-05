package com.example.healthcaresystem.doctor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDto {

    private String firstName;

    private String lastName;

    private String specialty;

    private String email;

}