package com.rest.endpoint.controllers;

import com.rest.dto.request.NewTherapistRequest;
import com.rest.dto.response.TherapistResponse;
import com.rest.endpoint.services.TherapistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/therapists")
@RestController
@RequiredArgsConstructor
public class TherapistController {

    private final TherapistService therapistService;

    @GetMapping
    public ResponseEntity<List<TherapistResponse>> getAllTherapists() {
        List<TherapistResponse> therapists = therapistService.getAllTherapists();
        return ResponseEntity.ok(therapists);
    }

    @PostMapping
    public ResponseEntity<TherapistResponse> createTherapist(@RequestBody NewTherapistRequest newTherapist) {
        TherapistResponse createdTherapist = therapistService.createTherapist(newTherapist);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTherapist);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TherapistResponse> getTherapistById(@PathVariable Long id) {
        TherapistResponse therapist = therapistService.getTherapistById(id);
        return ResponseEntity.ok(therapist);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TherapistResponse> updateTherapist(@PathVariable Long id, @RequestBody NewTherapistRequest therapistData) {
        TherapistResponse updatedTherapist = therapistService.updateTherapist(id, therapistData);
        return ResponseEntity.ok(updatedTherapist);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTherapist(@PathVariable Long id) {
        therapistService.deleteTherapist(id);
        return ResponseEntity.noContent().build();
    }
}
