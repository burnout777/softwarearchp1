package com.example.part1.repo;

import com.example.part1.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    // Additional query methods if needed
    // For example, finding a doctor by email:
    Doctor findByEmail(String email);
}

