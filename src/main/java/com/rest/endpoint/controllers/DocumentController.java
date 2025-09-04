package com.rest.endpoint.controllers;

import com.rest.dto.request.NewDocumentRequest;
import com.rest.dto.response.DocumentsResponse;
import com.rest.entity.Document;
import com.rest.endpoint.services.DocumentService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
public class DocumentController {
  private final DocumentService documentService;

  @GetMapping("/documents")
  public ResponseEntity<List<DocumentsResponse>> getAllDocuments() {
    List<DocumentsResponse> documents = documentService.getAllDocuments();
    return ResponseEntity.ok(documents);
  }

  @PostMapping("/documents")
  public ResponseEntity<Document> createDocument(@RequestBody NewDocumentRequest newDocument) {
    Document createdDocument = documentService.createDocument(newDocument);
    return ResponseEntity.ok(createdDocument);
  }

  @GetMapping("/documents/{id}")
  public ResponseEntity<Document> getDocumentById(@PathVariable Long id) {
    Document document = documentService.getDocumentById(id);
    return ResponseEntity.ok(document);
  }

  @PutMapping("/documents/{id}")
  public ResponseEntity<Document> updateDocument(@PathVariable Long id, @RequestBody Document newDocumentData) {
    Document updatedDocument = documentService.updateDocument(id, newDocumentData);
    return ResponseEntity.ok(updatedDocument);
  }

  @DeleteMapping("/documents/{id}")
  public ResponseEntity<Void> deleteDocument(@PathVariable Long id) {
    documentService.deleteDocument(id);
    return ResponseEntity.noContent().build(); // Retorna 204 No Content, uma prática comum para DELETE
  }
}