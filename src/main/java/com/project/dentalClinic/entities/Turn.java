package com.project.dentalClinic.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "Turns")
public class Turn {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name= "dentist_id")
    private Dentist dentist;
    @Column
    private Date date;

    @Override
    public String toString() {
        return "Turn{" +
                "id=" + id +
                ", patient =" + patient +
                ", dentist =" + dentist +
                ", date =" + date +
                '}';
    }
}
