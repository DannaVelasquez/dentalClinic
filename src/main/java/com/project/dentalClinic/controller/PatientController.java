package com.project.dentalClinic.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.dentalClinic.Exceptions.BadRequestException;
import com.project.dentalClinic.Exceptions.ResourceNotFoundException;
import com.project.dentalClinic.dto.PatientDto;
import com.project.dentalClinic.entities.Patient;
import com.project.dentalClinic.services.impl.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/new")
    public ResponseEntity<PatientDto> savePatient(@RequestBody PatientDto patient) throws BadRequestException, ResourceNotFoundException {
        PatientDto savedPatient = patientService.savePatient(patient);
        return ResponseEntity.ok().body(savedPatient);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> searchPatient(@PathVariable Integer id) throws ResourceNotFoundException{
        ResponseEntity<PatientDto> response = null;
        if(patientService.searchPatient(id).isPresent()){
            PatientDto patientDto = patientService.searchPatient(id).orElse(null);
            response = ResponseEntity.ok().body(patientDto);
        }else{
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }
//
    @GetMapping
    public ResponseEntity<List<PatientDto>> searchAll() throws ResourceNotFoundException{
        return ResponseEntity.ok(patientService.listAll());
    }
//
    @PutMapping("/update")
    public ResponseEntity<PatientDto> updatePatient(@RequestBody PatientDto patientDto) throws BadRequestException, ResourceNotFoundException{
        ResponseEntity<PatientDto> response = null;
        if(patientDto.getId() != null && patientService.searchPatient(patientDto.getId()).isPresent()){
            response = ResponseEntity.ok(patientService.updatePatient(patientDto));
        }else{
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Integer id) throws ResourceNotFoundException{
        ResponseEntity<String> response = null;

        if((patientService.searchPatient(id).isPresent())){
            patientService.deletePatient(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else{
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }
}
