package com.example.healthcaresystem.merdicalrecord;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical-records")
@SecurityRequirement(name = "auth")
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    @Autowired
    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @PostMapping
    public ResponseEntity<MedicalRecord> createMedicalRecord(@RequestBody CreateMedicalRecordDto dto) {
        MedicalRecord record = medicalRecordService.createMedicalRecord(dto);
        return ResponseEntity.ok(record);
    }

    @GetMapping
    public ResponseEntity<List<MedicalRecord>> getAllMedicalRecords(
            @RequestParam(required = false) Long patientId
    ) {
        List<MedicalRecord> records = medicalRecordService.getAllMedicalRecords(patientId);
        return ResponseEntity.ok(records);
    }


    @GetMapping("/{id}")
    public ResponseEntity<MedicalRecord> getMedicalRecordById(@PathVariable Long id) {
        return medicalRecordService.getMedicalRecordById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicalRecord> updateMedicalRecord(
            @PathVariable Long id,
            @RequestBody CreateMedicalRecordDto dto
    ) {
        return medicalRecordService.updateMedicalRecord(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicalRecord(@PathVariable Long id) {
        boolean deleted = medicalRecordService.deleteMedicalRecord(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}