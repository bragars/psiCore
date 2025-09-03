package com.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
public class TherapistsAndPatientsId implements Serializable {

    private @Getter @Setter Long therapistId;
    private @Getter @Setter Long patientId;

    // Required: equals & hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TherapistsAndPatientsId that)) return false;
        return Objects.equals(therapistId, that.therapistId) &&
                Objects.equals(patientId, that.patientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(therapistId, patientId);
    }
}
