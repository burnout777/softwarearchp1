package com.example.part1.controller;

import com.example.part1.domain.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medical-records")
public class MedicalRecordRestController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    // Create a new medical record
    @PostMapping
    public ResponseEntity<MedicalRecord> createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        MedicalRecord createdRecord = medicalRecordService.createMedicalRecord(medicalRecord);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRecord);
    }
}

