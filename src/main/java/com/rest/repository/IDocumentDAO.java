package com.rest.repository;

import com.rest.entity.Document;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IDocumentDAO extends JpaRepository<Document, Long> {
  Optional<Document> findById(Long id);
}
