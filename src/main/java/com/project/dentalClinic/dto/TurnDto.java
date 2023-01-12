package com.project.dentalClinic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.dentalClinic.entities.Dentist;
import com.project.dentalClinic.entities.Patient;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnDto {
    private Integer id;
    private Dentist dentist;
    private Patient patient;
    private Date date;
}
