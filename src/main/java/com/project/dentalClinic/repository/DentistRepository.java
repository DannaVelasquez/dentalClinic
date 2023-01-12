package com.project.dentalClinic.repository;

import com.project.dentalClinic.entities.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DentistRepository extends JpaRepository<Dentist,Integer> {

    //HQL Implementation
//    @Query("SELECT d FROM Dentist d WHERE d.name = ?1")
//    Dentist searchDentistByName(String dentistName);

    @Query("SELECT d FROM Dentist d WHERE d.register =?1")
    Dentist findByRegister(String register);
}
