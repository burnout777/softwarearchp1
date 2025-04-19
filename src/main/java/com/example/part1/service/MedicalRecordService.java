package com.example.part1.service;

import com.example.part1.domain.Appointment;
import com.example.part1.domain.MedicalRecord;
import com.example.part1.repo.AppointmentRepository;
import com.example.part1.repo.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalRecordService {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    /**
     * Create a new medical record
     * @param medicalRecord The medical record to create
     * @return The created medical record
     */
    public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) {
        return medicalRecordRepository.save(medicalRecord);
    }
}