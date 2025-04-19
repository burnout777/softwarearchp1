package com.example.part1.service;

import com.example.part1.domain.Appointment;
import com.example.part1.domain.Doctor;
import com.example.part1.repo.AppointmentRepository;
import com.example.part1.repo.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    /**
     * Get all doctors
     * @return List of all doctors
     */
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    /**
     * Create a new doctor
     * @param doctor The doctor to create
     * @return The created doctor
     */
    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    /**
     * Get a doctor by ID
     * @param id The doctor ID
     * @return The doctor or null if not found
     */
    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id).orElse(null);
    }

    /**
     * Update a doctor
     * @param id The doctor ID
     * @param doctorDetails The updated doctor details
     * @return The updated doctor or null if not found
     */
    public Doctor updateDoctor(Long id, Doctor doctorDetails) {
        return doctorRepository.findById(id).map(doctor -> {
            doctor.setName(doctorDetails.getName());
            doctor.setSpecialisation(doctorDetails.getSpecialisation());
            doctor.setEmail(doctorDetails.getEmail());
            doctor.setPhoneNumber(doctorDetails.getPhoneNumber());
            return doctorRepository.save(doctor);
        }).orElse(null);
    }

    /**
     * Delete a doctor
     * @param id The doctor ID
     * @return true if deleted, false if not found
     */
    public boolean deleteDoctor(Long id) {
        return doctorRepository.findById(id).map(doctor -> {
            // Remove doctor reference from appointments but don't delete the appointments
            List<Appointment> appointments = appointmentRepository.findByDoctorId(id);
            for (Appointment appointment : appointments) {
                appointment.setDoctor(null);
                appointmentRepository.save(appointment);
            }

            doctorRepository.delete(doctor);
            return true;
        }).orElse(false);
    }

    /**
     * Get all appointments for a doctor
     * @param id The doctor ID
     * @return List of appointments for the doctor
     */
    public List<Appointment> getAppointmentsForDoctor(Long id) {
        return appointmentRepository.findByDoctorId(id);
    }
}