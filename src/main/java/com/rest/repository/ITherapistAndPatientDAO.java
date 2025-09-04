package com.rest.repository;

import com.rest.entity.TherapistsAndPatients;
import com.rest.entity.TherapistsAndPatientsId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITherapistAndPatientDAO extends JpaRepository<TherapistsAndPatients, TherapistsAndPatientsId> {
}
