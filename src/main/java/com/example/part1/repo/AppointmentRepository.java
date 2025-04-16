package com.example.part1.repo;

import com.example.part1.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    // Find all appointments for a specific patient
    List<Appointment> findByPatientId(Long patientId);

    // Find all appointments for a specific doctor
    List<Appointment> findByDoctorId(Long doctorId);

    // Find the appointment by patient and doctor
    List<Appointment> findByPatientIdAndDoctorId(Long patientId, Long doctorId);
}

