package com.rest.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UpdatePatientRequest {
    private String fullName;
    private Date birthday;
    private String about;
}
