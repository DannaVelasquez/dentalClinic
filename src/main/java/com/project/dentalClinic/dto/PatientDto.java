package com.project.dentalClinic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientDto {
    private Integer id;
    private String lastname;
    private String name;
    private String dni;
    private Date entryDate;
    private AddressDto address;
}
