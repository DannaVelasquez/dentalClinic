package com.project.dentalClinic.services;

import com.project.dentalClinic.Exceptions.BadRequestException;
import com.project.dentalClinic.Exceptions.ResourceNotFoundException;
import com.project.dentalClinic.dto.PatientDto;
import java.util.List;
import java.util.Optional;

public interface IPatientService {
    PatientDto savePatient(PatientDto patientDto) throws BadRequestException;
    Optional<PatientDto> searchPatient(Integer id) throws ResourceNotFoundException;
    List<PatientDto> listAll();

    PatientDto updatePatient(PatientDto patient) throws BadRequestException;
    void deletePatient(Integer id) throws ResourceNotFoundException;
}
