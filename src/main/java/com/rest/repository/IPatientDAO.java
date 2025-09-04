package com.rest.repository;

import com.rest.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IPatientDAO extends JpaRepository<Patient, Long> {
//    Optional<Patient> findByName(String name);
}
