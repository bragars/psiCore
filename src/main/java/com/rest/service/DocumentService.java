package com.rest.service;

import com.rest.dto.request.NewDocumentRequest;
import com.rest.dto.response.DocumentsResponse;
import com.rest.entity.Document;
import com.rest.exception.BadRequestException;
import com.rest.exception.DocumentNotFoundException;
import com.rest.exception.UserNotFoundException;
import com.rest.repository.IDocumentDAO;
import com.rest.repository.IUserDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final IDocumentDAO documentRepository;
    private final IUserDAO userRepository;

    /**
     * Retorna uma lista de todos os documentos de forma simplificada.
     */
    public List<DocumentsResponse> getAllDocuments() {
        return documentRepository.findAll().stream().map(DocumentsResponse::new).collect(Collectors.toList());
    }

    /**
     * Retorna um documento específico pelo seu ID.
     */
    public Document getDocumentById(Long id) {
        return documentRepository.findById(id).orElseThrow(() -> new DocumentNotFoundException(id));
    }

    /**
     * Cria um novo documento associado a um usuário.
     */
    public Document createDocument(NewDocumentRequest newDocumentRequest) {
        Long userId = newDocumentRequest.getUserId();
        if (userId == null) {
            throw new BadRequestException("User ID is required");
        }

        var user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        var document = new Document();
        document.setName(newDocumentRequest.getName());
        document.setUser(user);

        return documentRepository.save(document);
    }

    /**
     * Atualiza um documento existente ou cria um novo se não existir.
     */
    public Document updateDocument(Long id, Document newDocumentData) {
        return documentRepository.findById(id).map(existingDocument -> {
            existingDocument.setName(newDocumentData.getName());
            return documentRepository.save(existingDocument);
        }).orElseGet(() -> {
            newDocumentData.setId(id); // Garante que o novo documento tenha o ID correto
            return documentRepository.save(newDocumentData);
        });
    }

    /**
     * Deleta um documento pelo seu ID.
     */
    public void deleteDocument(Long id) {
        if (!documentRepository.existsById(id)) {
            throw new DocumentNotFoundException(id);
        }
        documentRepository.deleteById(id);
    }
}
