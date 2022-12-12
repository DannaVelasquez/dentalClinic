package com.project.dentalClinic;

import com.project.dentalClinic.Exceptions.BadRequestException;
import com.project.dentalClinic.dto.DentistDto;
import com.project.dentalClinic.services.impl.DentistService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(JUnit4.class)
@SpringBootTest
public class DentistServiceTest {

    private static DentistService dentistService;

    @Ignore
    @BeforeClass
    public static void dataLoad() throws BadRequestException {
        dentistService.saveDentist(new DentistDto(1,"Velasquez", "Benji", "123"));
    }

    @Ignore
    @Test
    public void saveDentist() throws BadRequestException {
        DentistDto dentist = dentistService.saveDentist(new DentistDto(1,"Gomez", "Ana","123"));
        Assert.assertTrue(dentist.getId() != null);
    }
}
