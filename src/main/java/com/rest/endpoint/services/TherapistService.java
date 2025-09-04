package com.rest.endpoint.services;

import com.rest.dto.request.NewTherapistRequest;
import com.rest.dto.response.TherapistResponse;
import com.rest.entity.Therapist;
import com.rest.repository.ITherapistDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder; // Importante para senhas
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TherapistService {

    private final ITherapistDAO therapistRepository;
    private final PasswordEncoder passwordEncoder; // Injete o PasswordEncoder para hashear senhas

    @Transactional(readOnly = true)
    public List<TherapistResponse> getAllTherapists() {
        return therapistRepository.findAll()
                .stream()
                .map(TherapistResponse::new) // Converte cada Therapist para TherapistResponse
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TherapistResponse getTherapistById(Long id) {
        Therapist therapist = therapistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Therapist not found with id: " + id)); // Lançar uma exceção customizada é uma boa prática
        return new TherapistResponse(therapist);
    }

    @Transactional
    public TherapistResponse createTherapist(NewTherapistRequest request) {
        // Lógica para verificar se o email ou CRP já existem pode ser adicionada aqui

        Therapist therapist = new Therapist();

        // Populando os dados herdados de User
        therapist.setUsername(request.getUsername());
        therapist.setEmail(request.getEmail());
        therapist.setPassword(passwordEncoder.encode(request.getPassword())); // NUNCA salve a senha em texto plano

        // Populando os dados específicos de Therapist
        therapist.setCrp(request.getCrp());
        therapist.setBiography(request.getBiography());

        Therapist savedTherapist = therapistRepository.save(therapist);

        return new TherapistResponse(savedTherapist);
    }

    @Transactional
    public TherapistResponse updateTherapist(Long id, NewTherapistRequest requestData) {
        Therapist existingTherapist = therapistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Therapist not found with id: " + id));

        // Atualiza apenas os campos permitidos
        existingTherapist.setUsername(requestData.getUsername());
        existingTherapist.setBiography(requestData.getBiography());
        // Lógica para não permitir a alteração de email, crp ou senha nesta rota pode ser implementada

        Therapist updatedTherapist = therapistRepository.save(existingTherapist);

        return new TherapistResponse(updatedTherapist);
    }

    @Transactional
    public void deleteTherapist(Long id) {
        if (!therapistRepository.existsById(id)) {
            throw new RuntimeException("Therapist not found with id: " + id);
        }
        therapistRepository.deleteById(id);
    }
}
