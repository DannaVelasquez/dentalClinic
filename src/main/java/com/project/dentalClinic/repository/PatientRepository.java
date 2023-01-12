package com.project.dentalClinic.repository;

import com.project.dentalClinic.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Patient searchById(Integer id);

    Patient findByDni(String dni);
}
