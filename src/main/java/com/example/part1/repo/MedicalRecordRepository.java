package com.example.part1.repo;

import com.example.part1.domain.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    // Find the medical record by appointment ID
    MedicalRecord findByAppointmentId(Long appointmentId);

    static List<MedicalRecord> findByPatientId(Long id) {
        return null;
    }

    void deleteByAppointmentId(Long id);
}

