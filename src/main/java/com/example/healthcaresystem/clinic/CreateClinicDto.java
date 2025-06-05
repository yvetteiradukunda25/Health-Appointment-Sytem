package com.example.healthcaresystem.clinic;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateClinicDto {

    @Column(unique = true, length = 100, nullable = false)
    private String name;

    @Column( nullable = false)
    private String address;

    @Column(nullable = false,length = 20, unique = true)
    private String phone;
}
