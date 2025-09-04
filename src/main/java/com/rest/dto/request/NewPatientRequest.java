package com.rest.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class NewPatientRequest {
    // Dados User
    private String username;
    private String email;
    private String password;

    // Dados Patient
    private Date birthday;
    private String about;
}
