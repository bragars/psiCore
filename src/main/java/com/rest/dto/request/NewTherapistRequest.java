package com.rest.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewTherapistRequest {
    // Dados do User
    private String username;
    private String email;
    private String password; // A senha será recebida em texto plano e hasheada no serviço

    // Dados específicos do Therapist
    private String crp;
    private String biography;
}
