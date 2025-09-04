package com.rest.dto.response;

import com.rest.entity.Therapist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TherapistResponse {
    private Long id;
    private String username;
    private String email;
    private String crp;
    private String biography;

    public TherapistResponse(Therapist therapist) {
        this.id = therapist.getId();
        this.username = therapist.getUsername();
        this.email = therapist.getEmail();
        this.crp = therapist.getCrp();
        this.biography = therapist.getBiography();
    }
}
