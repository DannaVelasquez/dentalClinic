package com.project.dentalClinic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.dentalClinic.entities.Dentist;
import com.project.dentalClinic.entities.Patient;
import lombok.Data;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnDto {
    private Integer id;
    private Patient patient;
    private Dentist dentist;
    private Date date;
}
