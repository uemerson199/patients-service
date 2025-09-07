package com.hospitalcare.patients_service.dtos;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PatientRequestDTO {
    private String name;
    private LocalDate dob;
    private String cpf;
}