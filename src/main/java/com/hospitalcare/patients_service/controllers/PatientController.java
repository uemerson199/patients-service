package com.hospitalcare.patients_service.controllers;

import com.hospitalcare.patients_service.dtos.PatientRequestDTO;
import com.hospitalcare.patients_service.dtos.PatientResponseDTO;
import com.hospitalcare.patients_service.services.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable UUID id) {
        return patientService.getPatientById(id)
                .map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        List<PatientResponseDTO> patients = patientService.getAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PatientResponseDTO> createPatient(@RequestBody PatientRequestDTO requestDTO) {
        PatientResponseDTO responseDTO = patientService.createPatient(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PatientResponseDTO> update(@PathVariable UUID id,@RequestBody PatientRequestDTO dto) {
        PatientResponseDTO responseDTO = patientService.updatePatient(id, dto);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePatientById(@PathVariable UUID id) {
        patientService.deletePatientById(id);
        return ResponseEntity.noContent().build();
    }


}