package com.hospitalcare.patients_service.services;

import com.hospitalcare.patients_service.dtos.PatientRequestDTO;
import com.hospitalcare.patients_service.dtos.PatientResponseDTO;
import com.hospitalcare.patients_service.entities.Patient;
import com.hospitalcare.patients_service.exceptions.ResourceNotFoundException;
import com.hospitalcare.patients_service.mappers.PatientMapper;
import com.hospitalcare.patients_service.repositories.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientService {

    private final PatientRepository patientRepository;


    public Optional<PatientResponseDTO> getPatientById(UUID id) {
        log.info("Fetching patient with ID: {}", id);
        return patientRepository.findById(id)
                .map(PatientMapper::toResponseDTO);
    }

    public List<PatientResponseDTO> getAllPatients() {
        log.info("Fetching all patients");
        return patientRepository.findAll()
                .stream()
                .map(PatientMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public PatientResponseDTO createPatient(PatientRequestDTO requestDTO) {
        log.info("Creating new patient with CPF: {}", requestDTO.getCpf());
        Patient patientEntity = PatientMapper.toEntity(requestDTO);
        Patient savedEntity = patientRepository.save(patientEntity);
        return PatientMapper.toResponseDTO(savedEntity);
    }

    @Transactional
    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO requestDTO) {
        try {
            Patient patient = patientRepository.getReferenceById(id);

            patient.setName(requestDTO.getName());
            patient.setCpf(requestDTO.getCpf());
            patient.setDob(requestDTO.getDob());

            patient = patientRepository.save(patient);

            return PatientMapper.toResponseDTO(patient);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Resource Not Found");
        }
    }

    public void deletePatientById(UUID id) {
        if (!patientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Resource Not Found");
        }
        patientRepository.deleteById(id);
    }


}