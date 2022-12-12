package com.project.dentalClinic.services;

import com.project.dentalClinic.Exceptions.BadRequestException;
import com.project.dentalClinic.Exceptions.ResourceNotFoundException;
import com.project.dentalClinic.dto.TurnDto;
import java.util.List;
import java.util.Optional;

public interface ITurnService {
    TurnDto saveTurn(TurnDto turnDto) throws BadRequestException;
    Optional<TurnDto> searchTurn(Integer id) throws ResourceNotFoundException;
    List<TurnDto> listAll();
    TurnDto updateTurn(TurnDto turnDto) throws BadRequestException;

    void deleteTurn(Integer id) throws ResourceNotFoundException;
}
