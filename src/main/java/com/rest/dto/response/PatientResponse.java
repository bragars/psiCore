package com.rest.dto.response;

import com.rest.entity.Patient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientResponse {
    private Long id;
    private String username;
    private String email;
    private Date birthday;
    private String about;

    public PatientResponse(Patient patient) {
        this.id = patient.getId();
        this.username = patient.getUsername();
        this.email = patient.getEmail();
        this.birthday = patient.getBirthday();
        this.about = patient.getAbout();
    }
}
