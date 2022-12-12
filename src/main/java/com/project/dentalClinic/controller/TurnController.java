package com.project.dentalClinic.controller;

import com.project.dentalClinic.Exceptions.BadRequestException;
import com.project.dentalClinic.Exceptions.ResourceNotFoundException;
import com.project.dentalClinic.dto.TurnDto;
import com.project.dentalClinic.services.impl.DentistService;
import com.project.dentalClinic.services.impl.PatientService;
import com.project.dentalClinic.services.impl.TurnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/turns")
public class TurnController {
    private final TurnService turnService;
    private final DentistService dentistService;
    private final PatientService patientService;

    @Autowired
    public TurnController(TurnService turnService, DentistService dentistService, PatientService patientService) {
        this.turnService = turnService;
        this.dentistService = dentistService;
        this.patientService = patientService;
    }

    @PostMapping("/new")
    public ResponseEntity<TurnDto> saveTurn(@RequestBody TurnDto turnDto)
            throws BadRequestException, ResourceNotFoundException {
        ResponseEntity<TurnDto> response;
        if (patientService.searchPatient(turnDto.getPatient().getId()) != null &&
                dentistService.searchDentist(turnDto.getDentist().getId()) != null) {
            response = ResponseEntity.ok(turnService.saveTurn(turnDto));
        } else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnDto> searchPatient(@PathVariable Integer id) throws ResourceNotFoundException {
        ResponseEntity<TurnDto> response = null;
        if(turnService.searchTurn(id).isPresent()){
            TurnDto turnDto = turnService.searchTurn(id).orElse(null);
            response = ResponseEntity.ok().body(turnDto);
        }else{
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @GetMapping
    public ResponseEntity<List<TurnDto>> listAll() throws ResourceNotFoundException {
        return ResponseEntity.ok(turnService.listAll());
    }

    @PutMapping("/update")
    public ResponseEntity<TurnDto> updateTurn(@RequestBody TurnDto turnDto) throws BadRequestException, ResourceNotFoundException {
        ResponseEntity<TurnDto> response = null;
        if(turnDto.getId() != null && turnService.searchTurn(turnDto.getId()).isPresent()){
            response = ResponseEntity.ok(turnService.updateTurn(turnDto));
        }else{
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTurn(@PathVariable Integer id) throws ResourceNotFoundException {
        ResponseEntity<String> response;
        if (turnService.searchTurn(id) != null) {
            turnService.deleteTurn(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }
}
