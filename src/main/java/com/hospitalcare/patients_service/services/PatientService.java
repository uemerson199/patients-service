package com.hospitalcare.patients_service.services;

import com.hospitalcare.patients_service.dtos.PatientRequestDTO;
import com.hospitalcare.patients_service.dtos.PatientResponseDTO;
import com.hospitalcare.patients_service.entities.Patient;
import com.hospitalcare.patients_service.mappers.PatientMapper;
import com.hospitalcare.patients_service.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientResponseDTO createPatient(PatientRequestDTO requestDTO) {
        log.info("Creating new patient with CPF: {}", requestDTO.getCpf());
        Patient patientEntity = PatientMapper.toEntity(requestDTO);
        Patient savedEntity = patientRepository.save(patientEntity);
        return PatientMapper.toResponseDTO(savedEntity);
    }

    public Optional<PatientResponseDTO> getPatientById(UUID id) {
        log.info("Fetching patient with ID: {}", id);
        return patientRepository.findById(id)
                .map(PatientMapper::toResponseDTO);
    }
}