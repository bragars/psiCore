package com.rest.endpoint.services;

import com.rest.dto.request.NewPatientRequest;
import com.rest.dto.request.UpdatePatientRequest;
import com.rest.dto.response.PatientResponse;
import com.rest.entity.Patient;
import com.rest.repository.IPatientDAO;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final IPatientDAO patientRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public List<PatientResponse> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(PatientResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PatientResponse getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
        return new PatientResponse(patient);
    }

    @Transactional
    public PatientResponse createPatient(@NotNull NewPatientRequest request) {
        // Adicionar validação para verificar se o e-mail já existe
        // if (userRepository.existsByEmail(request.getEmail())) { ... }

        Patient patient = new Patient();

        // Populando os dados herdados de User
        patient.setUsername(request.getUsername());
        patient.setEmail(request.getEmail());
        patient.setPassword(passwordEncoder.encode(request.getPassword()));
        patient.setBirthday(request.getBirthday());

        // Populando os dados específicos de Patient
        patient.setAbout(request.getAbout());

        Patient savedPatient = patientRepository.save(patient);

        return new PatientResponse(savedPatient);
    }

    @Transactional
    public PatientResponse updatePatient(Long id, UpdatePatientRequest requestData) {
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));

        existingPatient.setUsername(requestData.getFullName());
        existingPatient.setAbout(requestData.getAbout());
        existingPatient.setBirthday(requestData.getBirthday());

        Patient updatedPatient = patientRepository.save(existingPatient);

        return new PatientResponse(updatedPatient);
    }

    @Transactional
    public void deletePatient(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new RuntimeException("Patient not found with id: " + id);
        }
        patientRepository.deleteById(id);
    }
}