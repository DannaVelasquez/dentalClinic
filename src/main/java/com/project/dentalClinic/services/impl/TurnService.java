package com.project.dentalClinic.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.dentalClinic.Exceptions.BadRequestException;
import com.project.dentalClinic.Exceptions.ResourceNotFoundException;
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
        Dentist dentist1 = objectMapper.convertValue(turnDto.getDentist().getId(), Dentist.class);
        Optional<Dentist> dentist = dentistRepository.findById(turnDto.getDentist().getId());
        Patient patient1 = objectMapper.convertValue(turnDto.getPatient().getId(), Patient.class);
        Optional<Patient> patient = patientRepository.findById(turnDto.getPatient().getId());
        Turn savedTurn;
        if (patient.isEmpty() || dentist.isEmpty()) {
            LOG.error("Patient or Dentist don't exist!");
            throw new BadRequestException("Patient or Dentist don't exist!");
        }
            turnDto.setDentist(dentist.get());
            turnDto.setPatient(patient.get());
            turnDto.setDate(new Date());
            LOG.info("Turn saved successfully!");
            Turn turn1 = objectMapper.convertValue(turnDto, Turn.class);
            savedTurn = turnRepository.save(turn1);
        return objectMapper.convertValue(savedTurn, TurnDto.class);
    }

    //Method to search turns by Id
    @Override
    public Optional<TurnDto> searchTurn(Integer id) throws ResourceNotFoundException{
        LOG.debug("Searching turn...");
        Optional<Turn> turnOptional = turnRepository.findById(id);
        Turn turn = turnOptional.orElse(null);
        if(turnRepository.findById(id).isEmpty()){
            LOG.error("Turn with id: " + id + " doesn't exist!");
            throw new ResourceNotFoundException("Turn with id: " + id + " doesn't exist!");
        }
        LOG.info("Turn was found!");
        Turn searchedTurn = turnRepository.findById(id).get();
        return Optional.ofNullable(objectMapper.convertValue(searchedTurn, TurnDto.class));
    }

    //Method to search all turns
    @Override
    public List<TurnDto> listAll() {
        LOG.debug("Listing all turns...");
        List<Turn> turnList = turnRepository.findAll();
        List<TurnDto> turnDtoList = new ArrayList<>();
        TurnDto turnDto;
        for(Turn turn : turnList){
            turnDto = objectMapper.convertValue(turn, TurnDto.class);
            turnDtoList.add(turnDto);
        }
        LOG.info("Turns listed!");
        return turnDtoList;
    }

    //Method to update a turn
    public TurnDto updateTurn(TurnDto turnDto) throws BadRequestException {
        LOG.debug("Updating turn...");
        LOG.info("Dentist updated!");
        return saveTurn(turnDto);
    }

    //Method to delete a turn
    @Override
    public void deleteTurn(Integer id) throws ResourceNotFoundException {
        LOG.debug("Deleting turn...");
        if(Objects.isNull(searchTurn(id))) {
            LOG.error("Turn with id: " + id + "doesn't exist!");
            throw new ResourceNotFoundException("Turn with id: " + id + "doesn't exist!");
        }
        LOG.info("Turn was deleted!");
        turnRepository.deleteById(id);
    }




}
