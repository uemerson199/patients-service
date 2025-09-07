package com.hospitalcare.patients_service.mappers;

import com.hospitalcare.patients_service.dtos.PatientRequestDTO;
import com.hospitalcare.patients_service.dtos.PatientResponseDTO;
import com.hospitalcare.patients_service.entities.Patient;

public class PatientMapper {

    public static Patient toEntity(PatientRequestDTO dto) {
        return Patient.builder()
                .name(dto.getName())
                .dob(dto.getDob())
                .cpf(dto.getCpf())
                .build();
    }

    public static PatientResponseDTO toResponseDTO(Patient entity) {
        PatientResponseDTO dto = new PatientResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDob(entity.getDob());
        dto.setCpf(entity.getCpf());
        return dto;
    }
}