package com.example.healthcaresystem.merdicalrecord;

import com.example.healthcaresystem.patient.Patient;
import com.example.healthcaresystem.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository, PatientRepository patientRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
        this.patientRepository = patientRepository;
    }

    public MedicalRecord createMedicalRecord(CreateMedicalRecordDto dto) {
        MedicalRecord record = new MedicalRecord();
        record.setDiagnosis(dto.getDiagnosis());
        record.setPrescription(dto.getPrescription());
        record.setRecordDate(dto.getRecordDate());
        record.setDescription(dto.getDescription());

        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));
        record.setPatient(patient);

        return medicalRecordRepository.save(record);
    }

    public List<MedicalRecord> getAllMedicalRecords(Long patientId) {
        if (patientId != null) {
            return medicalRecordRepository.findByPatientId(patientId);
        }
        return medicalRecordRepository.findAll();
    }

    public Optional<MedicalRecord> getMedicalRecordById(Long id) {
        return medicalRecordRepository.findById(id);
    }

    public Optional<MedicalRecord> updateMedicalRecord(Long id, CreateMedicalRecordDto dto) {
        return medicalRecordRepository.findById(id).map(record -> {
            if (dto.getDiagnosis() != null) record.setDiagnosis(dto.getDiagnosis());
            if (dto.getPrescription() != null) record.setPrescription(dto.getPrescription());
            if (dto.getRecordDate() != null) record.setRecordDate(dto.getRecordDate());
            if (dto.getDescription() != null) record.setDescription(dto.getDescription());
            return medicalRecordRepository.save(record);
        });
    }

    public boolean deleteMedicalRecord(Long id) {
        if (medicalRecordRepository.existsById(id)) {
            medicalRecordRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
