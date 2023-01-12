package com.project.dentalClinic.services;

import com.project.dentalClinic.exceptions.BadRequestException;
import com.project.dentalClinic.exceptions.ResourceNotFoundException;
import com.project.dentalClinic.dto.DentistDto;
import java.util.List;
import java.util.Optional;

public interface IDentistService {
    DentistDto saveDentist(DentistDto dentistDto) throws BadRequestException;
    Optional<DentistDto> searchDentist(Integer id) throws ResourceNotFoundException;
    List<DentistDto> listAll();
    DentistDto updateDentist(DentistDto dentist)throws BadRequestException;
    void deleteDentist(Integer id) throws ResourceNotFoundException;

    //Method implemented with HQL
//    List<DentistDto> searchDentistByName (String dentistName);
}
