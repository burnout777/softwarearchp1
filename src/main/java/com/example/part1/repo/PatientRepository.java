package com.example.part1.repo;

import com.example.part1.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    // Additional query methods if needed
    // For example, finding a patient by email:
    Patient findByEmail(String email);
}

