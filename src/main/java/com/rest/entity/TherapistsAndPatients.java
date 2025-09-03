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
public class TherapistsAndPatients {
    @EmbeddedId
    private TherapistsAndPatientsId id;

    @ManyToOne
    @MapsId("patientId")
    @JoinColumn(name = "patient_id")
    private @Getter @Setter Patient patient;

    @ManyToOne
    @MapsId("theRapistId")
    @JoinColumn(name = "therapist_id")
    private @Getter @Setter Therapist course;

    private @Getter @Setter Status status;
}
