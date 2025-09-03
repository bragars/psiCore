package com.rest.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

public class Patient extends User {
    private @Getter @Setter String about;
    private @Getter @Setter Date birthday;
}
