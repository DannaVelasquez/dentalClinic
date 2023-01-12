package com.project.dentalClinic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
@Setter
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DentistDto  {
    private Integer id;
    private String lastname;
    private String name;
    private String register;

}
