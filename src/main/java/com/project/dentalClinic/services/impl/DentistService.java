package com.project.dentalClinic.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.dentalClinic.Exceptions.BadRequestException;
import com.project.dentalClinic.Exceptions.ResourceNotFoundException;
import com.project.dentalClinic.dto.DentistDto;
import com.project.dentalClinic.repository.DentistRepository;
import com.project.dentalClinic.entities.Dentist;
import com.project.dentalClinic.services.IDentistService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DentistService implements IDentistService {
    private final DentistRepository dentistRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final static Logger LOG = Logger.getLogger(DentistService.class);

    //Dependency injected in order to consult the repository
    @Autowired
    public DentistService(DentistRepository dentistRepository) {
        this.dentistRepository = dentistRepository;
    }

    //Method to create a dentist
    @Override
    public DentistDto saveDentist(DentistDto dentistDto) throws BadRequestException {
        LOG.debug("Registering new dentist: " + dentistDto.toString());
        Dentist dentist = objectMapper.convertValue(dentistDto, Dentist.class);
        Dentist savedDentist = dentistRepository.save(dentist);
        if (savedDentist.equals(null)) {
            LOG.error("Dentist couldn't be saved!");
            throw new BadRequestException("Dentist couldn't be saved!");
        }
        LOG.info("Dentist saved successfully!");
        return objectMapper.convertValue(savedDentist, DentistDto.class);
    }

    //Method to search dentist by Id
    @Override
    public Optional<DentistDto> searchDentist(Integer id) throws ResourceNotFoundException {
        LOG.debug("Searching dentist....");
        Optional<Dentist> dentistOptional = dentistRepository.findById(id);
        Dentist dentistOp = dentistOptional.orElse(null);
        if(dentistRepository.findById(id).isEmpty()){
            LOG.error("Dentist with id: " + id + " doesn't exist!");
            throw new ResourceNotFoundException("Dentist with id: " + id + " doesn't exist!");
        }
        LOG.info("Dentist was found!");
        return Optional.ofNullable(objectMapper.convertValue(dentistOp, DentistDto.class));
        }

    //Method to search all dentists
    @Override
    public List<DentistDto> listAll() {
        LOG.debug("Listing all dentists...");
        List<Dentist> dentistList= dentistRepository.findAll();
        List<DentistDto> dentistDtoList = new ArrayList<>();
        DentistDto dentistDto;
        for(Dentist dentist : dentistList){
            dentistDto = objectMapper.convertValue(dentist, DentistDto.class);
            dentistDtoList.add(dentistDto);
        }
        LOG.info("Dentists listed!");
        return dentistDtoList;
    }

    //Method to update a dentist
    @Override
    public DentistDto updateDentist(DentistDto dentistDto) throws BadRequestException {
        LOG.debug("Updating dentist...");
        LOG.info("Dentist updated!");
        return saveDentist(dentistDto);
    }

    //Method to delete a dentist
    @Override
    public void deleteDentist(Integer id) throws ResourceNotFoundException{
        LOG.debug("Deleting dentist...");
        if(Objects.isNull(searchDentist(id))){
            LOG.error("Dentist with id: " + id + " couldn't be deleted because it doesn't exist!");
            throw new ResourceNotFoundException("Dentist with id: " + id + " couldn't be deleted because it doesn't exist!");
        }
        LOG.info("Dentist was deleted!");
        dentistRepository.deleteById(id);
    }

    //Method implemented with HQL - Search dentist by name
//    @Override
//    public List<DentistDto> searchDentistByName(String dentistName){
//        LOG.debug("Listing all dentists...");
//        List<Dentist> dentistList= Collections.singletonList(dentistRepository.searchDentistByName(dentistName));
//        List<DentistDto> dentistDtoList = new ArrayList<>();
//        DentistDto dentistDto;
//        for(Dentist dentist : dentistList){
//            dentistDto = objectMapper.convertValue(dentist, DentistDto.class);
//            dentistDtoList.add(dentistDto);
//        }
//        LOG.info("Dentists listed!");
//        return dentistDtoList;
//    }
}
