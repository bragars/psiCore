package com.rest.repository;

import com.rest.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDocumentDAO extends JpaRepository<Document, Long> {
  // Remove the findById method since it's already defined in JpaRepository
  // with @NonNull Optional<Document> findById(@NonNull Long id)
}
