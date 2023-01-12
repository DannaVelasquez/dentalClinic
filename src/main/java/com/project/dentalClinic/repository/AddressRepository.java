package com.project.dentalClinic.repository;

import com.project.dentalClinic.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
