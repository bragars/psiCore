package com.rest.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "therapists")
@DiscriminatorValue("THERAPIST")
public class Therapist extends User {
    private @Getter @Setter String crp;
    private @Getter @Setter String biography;
}
