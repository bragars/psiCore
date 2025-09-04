package com.rest.entity;

import com.rest.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Entity
public class TherapistsAndPatients {
    @EmbeddedId
    private TherapistsAndPatientsId id;

    @ManyToOne
    @MapsId("patientId")
    @JoinColumn(name = "patient_id")
    private @Getter @Setter Patient patient;

    @ManyToOne
    @MapsId("therapistId")
    @JoinColumn(name = "therapist_id")
    private @Getter @Setter Therapist therapist;

    private @Getter @Setter Status status;
}
