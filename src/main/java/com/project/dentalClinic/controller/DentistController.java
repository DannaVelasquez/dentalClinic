package com.project.dentalClinic.controller;

import com.project.dentalClinic.Exceptions.BadRequestException;
import com.project.dentalClinic.Exceptions.ResourceNotFoundException;
import com.project.dentalClinic.dto.DentistDto;
import com.project.dentalClinic.services.impl.DentistService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/dentists")
public class DentistController {
    private final DentistService dentistService;

    private final static Logger LOG = Logger.getLogger(DentistService.class);

    //Dependency injected in order to consult the services
    @Autowired
    public DentistController(DentistService dentistService) {
        this.dentistService = dentistService;
    }

    @PostMapping("/new")
    public ResponseEntity<DentistDto> saveDentist(@RequestBody DentistDto dentist) throws BadRequestException {
        DentistDto savedDentist = dentistService.saveDentist(dentist);
        return ResponseEntity.ok().body(savedDentist);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DentistDto> searchDentist(@PathVariable Integer id) throws ResourceNotFoundException {
        ResponseEntity<DentistDto> response = null;
        if (dentistService.searchDentist(id).isPresent()) {
            DentistDto dentistDto = dentistService.searchDentist(id).orElse(null);
            response = ResponseEntity.ok().body(dentistDto);
        }else{
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @GetMapping
    public ResponseEntity<List<DentistDto>> searchAll() throws ResourceNotFoundException{
        return ResponseEntity.ok(dentistService.listAll());
    }

    @PutMapping("/update")
    public ResponseEntity<DentistDto> updateDentist(@RequestBody DentistDto dentistDto) throws BadRequestException, ResourceNotFoundException {
        ResponseEntity<DentistDto> response = null;
        if (dentistDto.getId() != null && dentistService.searchDentist(dentistDto.getId()).isPresent()) {
            response = ResponseEntity.ok(dentistService.updateDentist(dentistDto));
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDentist(@PathVariable Integer id) throws ResourceNotFoundException{
        ResponseEntity<String> response = null;
        if((dentistService.searchDentist(id)).isPresent()){
            dentistService.deleteDentist(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else{
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    ///Method implemented with HQL
//    @GetMapping("/{dentistName}")
//    public ResponseEntity<List<DentistDto>> searchDentistByName(@PathVariable String dentistName){
//        return ResponseEntity.ok(dentistService.searchDentistByName(dentistName));
//    }
}

