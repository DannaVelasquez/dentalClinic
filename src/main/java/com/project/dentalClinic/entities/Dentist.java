package com.project.dentalClinic.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "Dentists")
@NoArgsConstructor
public class Dentist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String lastname;
    @Column
    private String name;
    @Column
    private String register;

    @OneToMany(mappedBy = "dentist", fetch = FetchType.LAZY)
    private List<Turn> turns;

    @Override
    public String toString(){
        return "Dentist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", register='" + register + '\'' +
                '}';
    }

}
