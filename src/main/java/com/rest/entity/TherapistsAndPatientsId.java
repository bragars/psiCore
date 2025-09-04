package com.rest.entity;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class TherapistsAndPatientsId implements Serializable {
    private @Getter @Setter Long therapistId;
    private @Getter @Setter Long patientId;
}
