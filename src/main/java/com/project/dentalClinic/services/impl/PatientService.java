package com.project.dentalClinic.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.dentalClinic.Exceptions.BadRequestException;
import com.project.dentalClinic.Exceptions.ResourceNotFoundException;
import com.project.dentalClinic.dto.AddressDto;
import com.project.dentalClinic.dto.PatientDto;
import com.project.dentalClinic.entities.Address;
import com.project.dentalClinic.entities.Patient;
import com.project.dentalClinic.repository.AddressRepository;
import com.project.dentalClinic.repository.PatientRepository;
import com.project.dentalClinic.services.IPatientService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PatientService implements IPatientService {
    private final PatientRepository patientRepository;
    private final AddressRepository addressRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final static Logger LOG = Logger.getLogger(PatientService.class);

    //Dependency injected in order to consult the repository
    @Autowired
    public PatientService(PatientRepository patientRepository, AddressRepository addressRepository) {
        this.patientRepository = patientRepository;
        this.addressRepository = addressRepository;
    }

    //Method to create a patient
    @Override
    public PatientDto savePatient(PatientDto patientDto) throws BadRequestException {
        LOG.debug("Registering new patient:" + patientDto.toString());
        Address addressDto = objectMapper.convertValue(patientDto.getAddress(), Address.class);
        Address savedAddress = addressRepository.save(addressDto);
        Patient patient = objectMapper.convertValue(patientDto, Patient.class);
        patient.setAddress(savedAddress);
        Patient savedPatient = patientRepository.save(patient);
        if(savedAddress.equals(null) || savedPatient.equals(null)){
            LOG.error("Patient couldn't be created!");
            throw new BadRequestException("Patient couldn't be created!");
        }
        LOG.info("Patient saved successfully!");
        return objectMapper.convertValue(patient, PatientDto.class);
    }

    //Method to search patient by Id
    @Override
    public Optional<PatientDto> searchPatient(Integer id) throws ResourceNotFoundException {
        LOG.debug("Searching patient...");
        Optional<Patient> patientOptional = patientRepository.findById(id);
        Patient patientOp = patientOptional.orElse(null);
        if(patientRepository.findById(id).isEmpty()){
            LOG.error("Patient with id: " + id + "doesn't exist!");
            throw new ResourceNotFoundException("Patient with id: " + id + "doesn't exist!");
        }
        LOG.info("Patient was found!");
        return Optional.ofNullable(objectMapper.convertValue(patientOp, PatientDto.class));
    }

    //Method to search all patients
    @Override
    public List<PatientDto> listAll() {
        LOG.debug("Listing all patients...");
        List<Patient> patientList = patientRepository.findAll();
        List<PatientDto> patientDtoList = new ArrayList<>();
        PatientDto patientDto;
        for(Patient patient : patientList){
            patientDto = objectMapper.convertValue(patient, PatientDto.class);
            patientDtoList.add(patientDto);
        }
        LOG.info("Patients listed!");
        return patientDtoList;
    }

    //Method to update a patient
    @Override
    public PatientDto updatePatient(PatientDto patient) throws BadRequestException{
        LOG.debug("Updating patient...");
        LOG.info("Patient updated!");
        return savePatient(patient);
    }

    //Method to delete a patient
    @Override
    public void deletePatient(Integer id) throws ResourceNotFoundException {
        LOG.debug("Deleting patient...");
        if(Objects.isNull(searchPatient(id))){
            LOG.error("Patient with id: " + id + "couldn't be deleted because it doesn't exist!");
            throw new ResourceNotFoundException("Patient with id: " + id + "couldn't be deleted because it doesn't exist!");
        }
        LOG.info("Patient was deleted!");
        patientRepository.deleteById(id);
    }
}
