package com.rest.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

public class Therapist extends User {
    private @Getter @Setter String crp;
    private @Getter @Setter String biography;
    private @Getter @Setter Date birthday;
}
