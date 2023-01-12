package com.project.dentalClinic;

import com.project.dentalClinic.entities.Dentist;
import com.project.dentalClinic.exceptions.BadRequestException;
import com.project.dentalClinic.dto.DentistDto;
import com.project.dentalClinic.exceptions.ResourceNotFoundException;
import com.project.dentalClinic.repository.DentistRepository;
import com.project.dentalClinic.services.IDentistService;
import com.project.dentalClinic.services.impl.DentistService;
import org.junit.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(JUnit4.class)
@SpringBootTest
public class DentistServiceTest {

    @Autowired
     private static IDentistService dentistService;
    @Autowired
     private static DentistRepository dentistRepository;

    public void data() throws BadRequestException {
        dentistService = new DentistService(dentistRepository);
        DentistDto dentistDto = dentistService.saveDentist(new DentistDto());
//        DentistDto dentistDto = new DentistDto();
        dentistDto.setId(1);
        dentistDto.setName("Benji");
        dentistDto.setLastname("Jonas");
        dentistDto.setRegister("12345");
//        dentistService.saveDentist(dentistDto);
    }

    @Test
    public void saveDentist() throws BadRequestException, ResourceNotFoundException {
        this.data();
        DentistDto dentistDto = dentistService.saveDentist(new DentistDto());
        dentistDto.setId(1);
        dentistDto.setName("Benji");
        dentistDto.setLastname("Jonas");
        dentistDto.setRegister("12345");

       Assert.assertNotNull(dentistService.searchDentist(dentistDto.getId()));

    }

    @Test
    public void deleteDentist() throws ResourceNotFoundException {
        dentistService.deleteDentist(1);
        Assert.assertTrue(dentistService.searchDentist(1).isEmpty());

    }
}

