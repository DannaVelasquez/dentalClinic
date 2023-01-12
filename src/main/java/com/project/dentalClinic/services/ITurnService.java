package com.project.dentalClinic.services;

import com.project.dentalClinic.exceptions.BadRequestException;
import com.project.dentalClinic.exceptions.ResourceNotFoundException;
import com.project.dentalClinic.dto.TurnDto;

import java.util.List;
import java.util.Optional;

public interface ITurnService {
    TurnDto saveTurn(TurnDto turnDto) throws BadRequestException;
    List<TurnDto> listAll();
    List<TurnDto> getAllByDentistRegister(String register);
    List<TurnDto> getAllByPatientDni(String dni);
    void deleteTurn(TurnDto turnDto) throws ResourceNotFoundException;
}
