package com.example.part1.service;

import com.example.part1.domain.Appointment;
import com.example.part1.domain.MedicalRecord;
import com.example.part1.repo.AppointmentRepository;
import com.example.part1.repo.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    /**
     * Get all appointments
     * @return List of all appointments
     */
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    /**
     * Create a new appointment
     * @param appointment The appointment to create
     * @return The created appointment
     */
    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    /**
     * Get an appointment by ID
     * @param id The appointment ID
     * @return The appointment or null if not found
     */
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    /**
     * Update an appointment
     * @param id The appointment ID
     * @param appointmentDetails The updated appointment details
     * @return The updated appointment or null if not found
     */
    public Appointment updateAppointment(Long id, Appointment appointmentDetails) {
        return appointmentRepository.findById(id).map(appointment -> {
            appointment.setAppointmentDate(appointmentDetails.getAppointmentDate());
            appointment.setStatus(appointmentDetails.getStatus());
            appointment.setNotes(appointmentDetails.getNotes());
            appointment.setPatient(appointmentDetails.getPatient());
            appointment.setDoctor(appointmentDetails.getDoctor());
            return appointmentRepository.save(appointment);
        }).orElse(null);
    }

    /**
     * Delete an appointment
     * @param id The appointment ID
     * @return true if deleted, false if not found
     */
    public boolean deleteAppointment(Long id) {
        return appointmentRepository.findById(id).map(appointment -> {
            // Delete associated medical record
            medicalRecordRepository.deleteByAppointmentId(id);

            appointmentRepository.delete(appointment);
            return true;
        }).orElse(false);
    }

    /**
     * Get the medical record for an appointment
     * @param id The appointment ID
     * @return The medical record or null if not found
     */
    public MedicalRecord getMedicalRecordForAppointment(Long id) {
        return medicalRecordRepository.findByAppointmentId(id);
    }
}