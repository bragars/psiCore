package com.rest.endpoint.Controllers;

import com.rest.exception.BadRequestException;
import com.rest.exception.DocumentNotFoundException;
import com.rest.exception.UserNotFoundException;
import com.rest.dto.request.NewDocumentRequest;
import com.rest.dto.response.DocumentsResponse;
import com.rest.entity.Document;
import com.rest.repository.IDocumentDAO;
import com.rest.repository.IUserDAO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/v1")
@RestController
public class DocumentController {
  private final IDocumentDAO documentRepository;
  private final IUserDAO userRepository;

  public DocumentController(IDocumentDAO documentRepository, IUserDAO userRepository) {
    this.documentRepository = documentRepository;
    this.userRepository = userRepository;
  }

  @GetMapping("/documents")
  ResponseEntity<List<DocumentsResponse>> all() {
    List<DocumentsResponse> documents = documentRepository.findAll().stream()
        .map(document -> DocumentsResponse.builder()
            .id(document.getId())
            .name(document.getName())
            .build())
        .collect(Collectors.toList()); // Use .collect(Collectors.toList()) for older Java versions

    return ResponseEntity.ok(documents);
  }

  @PostMapping("/documents")
  Document newDocument(@RequestBody NewDocumentRequest newDocument) {
    var userId = newDocument.getUserId();

    // Check if userId is provided
    if (userId == null) {
      throw new BadRequestException("User ID is required");
    }

    var user = userRepository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException(userId));

    var document = new Document();
    document.setName(newDocument.getName());
    document.setUser(user);

    return documentRepository.save(document);
  }

  @GetMapping("/documents/{id}")
  Document getById(@PathVariable Long id) {
    return documentRepository.findById(id)
        .orElseThrow(() -> new DocumentNotFoundException(id));
  }

  @PutMapping("/documents/{id}")
  Document replaceDocument(@RequestBody Document newDocument, @PathVariable Long id) {
    return documentRepository.findById(id)
        .map(document -> {
          document.setName(newDocument.getName());
          return documentRepository.save(document);
        })
        .orElseGet(() -> documentRepository.save(newDocument));
  }

  @DeleteMapping("/documents/{id}")
  void deleteUser(@PathVariable Long id) {
    documentRepository.deleteById(id);
  }
}
