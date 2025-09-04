package com.rest.repository;

import com.rest.entity.Therapist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ITherapistDAO extends JpaRepository<Therapist, Long> {
    Optional<Therapist> findByCrp(String crp);
}
