package com.project.dentalClinic.repository;

import com.project.dentalClinic.entities.Turn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurnRepository extends JpaRepository<Turn, Integer> {
    List<Turn> findAllByDentistRegister(String register);

    List<Turn> findAllByPatientDni(String dni);
}
