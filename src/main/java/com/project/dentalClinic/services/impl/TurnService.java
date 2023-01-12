package com.project.dentalClinic.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.dentalClinic.exceptions.BadRequestException;
import com.project.dentalClinic.exceptions.ResourceNotFoundException;
import com.project.dentalClinic.dto.TurnDto;
import com.project.dentalClinic.entities.Dentist;
import com.project.dentalClinic.entities.Patient;
import com.project.dentalClinic.entities.Turn;
import com.project.dentalClinic.repository.DentistRepository;
import com.project.dentalClinic.repository.PatientRepository;
import com.project.dentalClinic.repository.TurnRepository;
import com.project.dentalClinic.services.ITurnService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TurnService implements ITurnService {

    private final static Logger LOG = Logger.getLogger(TurnService.class);
    private final TurnRepository turnRepository;
    private final DentistRepository dentistRepository;
    private final PatientRepository patientRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public TurnService(TurnRepository turnRepository, DentistRepository dentistRepository, PatientRepository patientRepository) {
        this.turnRepository = turnRepository;
        this.dentistRepository = dentistRepository;
        this.patientRepository = patientRepository;
    }

    //Method to create turns
    @Override
    public TurnDto saveTurn (TurnDto turnDto) throws BadRequestException {
        LOG.debug("Creating a turn...");
        Optional<Dentist> dentist = dentistRepository.findById(turnDto.getDentist().getId());
        Optional<Patient> patient = patientRepository.findById(turnDto.getPatient().getId());
        Turn turn = mapToEntity(turnDto);
        turn.setPatient(patient.get());
        turn.setDentist(dentist.get());
        turn.setDate(new Date());
        Turn savedTurn = turnRepository.save(turn);
        return objectMapper.convertValue(turn, TurnDto.class);
    }

    @Override
    public void deleteTurn(TurnDto turnDto) {
        turnRepository.delete(mapToEntity(turnDto));
    }

    @Override
    public List<TurnDto> listAll() {
        return turnRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<TurnDto> getAllByDentistRegister(String register) {
        return turnRepository.findAllByDentistRegister(register).stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<TurnDto> getAllByPatientDni(String dni) {
        return turnRepository.findAllByPatientDni(dni).stream().map(this::mapToDto).collect(Collectors.toList());
    }


    private Turn mapToEntity(TurnDto turnDto) {
        return objectMapper.convertValue(turnDto, Turn.class);
    }

    private TurnDto mapToDto(Turn turn) {
        return objectMapper.convertValue(turn, TurnDto.class);
    }


}
