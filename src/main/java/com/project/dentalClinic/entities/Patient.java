package com.project.dentalClinic.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "Patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String lastname;
    @Column
    private String name;
    @Column
    private String dni;
    @Column
    private Date entryDate;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private List<Turn> turns;

    @Override
    public String toString(){
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", dni='" + dni + '\'' +
                ", entryDate='" + entryDate + '\''+
                ", address='" + address + '\''+
                '}';
    }
}
