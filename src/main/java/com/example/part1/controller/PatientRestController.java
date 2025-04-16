package com.example.part1.controller;

import com.example.part1.domain.Appointment;
import com.example.part1.domain.MedicalRecord;
import com.example.part1.domain.Patient;
import com.example.part1.repo.AppointmentRepository;
import com.example.part1.repo.MedicalRecordRepository;
import com.example.part1.repo.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientRestController {

    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    // Endpoint #1 - List all patients
    @GetMapping
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // Endpoint #2 - Create a new patient
    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        return patientRepository.save(patient);
    }

    // Endpoint #3 - Retrieve a patient by ID
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        return patientRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint #4 - Update a patient
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient updatedPatient) {
        return patientRepository.findById(id).map(patient -> {
            patient.setName(updatedPatient.getName());
            patient.setEmail(updatedPatient.getEmail());
            patient.setPhoneNumber(updatedPatient.getPhoneNumber());
            patient.setAddress(updatedPatient.getAddress());
            return ResponseEntity.ok(patientRepository.save(patient));
        }).orElse(ResponseEntity.notFound().build());
    }

    // Endpoint #5 - Delete a patient (and related appointments and records)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        if (!patientRepository.existsById(id)) return ResponseEntity.notFound().build();

        // Delete appointments and their medical records
        List<Appointment> appointments = appointmentRepository.findByPatientId(id);
        for (Appointment appointment : appointments) {
            medicalRecordRepository.deleteByAppointmentId(appointment.getId());
        }
        appointmentRepository.deleteAll(appointments);

        // Delete patient
        patientRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint #6 - List all appointments for a patient
    @GetMapping("/{id}/appointments")
    public List<Appointment> getAppointmentsForPatient(@PathVariable Long id) {
        return appointmentRepository.findByPatientId(id);
    }

    // Endpoint #7 - List all medical records for a patient
    @GetMapping("/{id}/medical-records")
    public List<MedicalRecord> getMedicalRecordsForPatient(@PathVariable Long id) {
        return MedicalRecordRepository.findByPatientId(id);
    }
}

