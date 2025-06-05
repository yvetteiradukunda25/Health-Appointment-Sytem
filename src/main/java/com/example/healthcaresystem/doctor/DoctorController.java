package com.example.healthcaresystem.doctor;

import com.example.healthcaresystem.patient.Patient;
import com.example.healthcaresystem.patient.PatientDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    @PostMapping("/api/create")
    @Operation(summary = "Add a new doctor")
    public Doctor createDoctor(@RequestBody DoctorDto doctorDto) {
       Doctor doctor = new Doctor();
       doctor.setFirstName(doctorDto.getFirstName());
       doctor.setLastName(doctorDto.getLastName());
       doctor.setSpecialty(doctorDto.getSpecialty());
       doctor.setEmail(doctorDto.getEmail());

        return doctorService.CreateDoctor(doctor);
    }

    @GetMapping
    @Operation(summary = "Get All doctors")
    public List<Doctor> findAllDoctor() {
        return doctorService.findAllDoctor();
    }

    @GetMapping("/api/{id}")
    @Operation(summary = "Find a doctor by id")
    public Doctor findDoctorById(@PathVariable long id) {
        return doctorService.findDoctorById(id);

    }

    @PutMapping("api/update/{id}")
    @Operation(summary = "Update the doctor")
    public Doctor update(@PathVariable long id, @RequestBody DoctorDto doctorDto) {
       Doctor doctor = doctorService.findDoctorById(id);
        doctor.setFirstName(doctorDto.getFirstName());
        doctor.setLastName(doctorDto.getLastName());
        doctor.setSpecialty(doctorDto.getSpecialty());
        doctor.setEmail(doctorDto.getEmail());
        return doctorService.update(id, doctorDto);
    }

    @DeleteMapping("/Api/delete/{id}")
    public void deleteDoctor(@PathVariable long id) {
        doctorService.deleteDoctor(id);
    }
}
