package com.rest.Feature;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.TestConfig;
import com.rest.endpoint.Controllers.DocumentController;
import com.rest.entity.Document;
import com.rest.entity.User;
import com.rest.repository.IDocumentDAO;
import com.rest.repository.IUserDAO;
import com.rest.service.JwtService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import(TestConfig.class)
@ExtendWith(MockitoExtension.class)

// only the DocumentController is loaded in the test context, not your
// authentication controller.
@WebMvcTest(DocumentController.class)

@WithMockUser(username = "user", roles = { "ADMIN" })
public class DocumentControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private JwtService jwtService;

  @MockBean
  private IDocumentDAO documentRepository;

  @MockBean
  private IUserDAO iUserDAO;

  @MockBean
  private UserDetails userDetails;

  private static final Logger LOGGER = LogManager.getLogger();

  @Test
  public void shouldReturnAllDocuments() throws Exception {

    // Mock Data
    Document doc1 = new Document();
    doc1.setId(1L);
    doc1.setName("Document 1");

    Document doc2 = new Document();
    doc2.setId(2L);
    doc2.setName("Document 2");

    List<Document> documents = Arrays.asList(doc1, doc2);

    // Mock repository behavior
    when(documentRepository.findAll()).thenReturn(documents);

    // Perform GET request and verify response
    mvc.perform(get("/api/v1/documents")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$.length()").value(2))
        .andExpect(jsonPath("$[0].id").value(1))
        .andExpect(jsonPath("$[0].name").value("Document 1"))
        .andExpect(jsonPath("$[1].id").value(2))
        .andExpect(jsonPath("$[1].name").value("Document 2"));
  }

  @Test
  public void shouldCreateDocument() throws Exception {

    // Mock Data
    Document document = new Document();
    document.setId(1L);
    document.setName("Document 1");

    // Mock Data
    User user = new User("root", "root@root.com", "root");
    user.setId(1L);

    ObjectMapper objectMapper = new ObjectMapper();

    // Create request body
    Map<String, Object> requestBody = new HashMap<>();
    requestBody.put("name", document.getName());
    requestBody.put("userId", user.getId());

    // Convert to JSON
    String jsonContent = objectMapper.writeValueAsString(requestBody);
    System.out.println(jsonContent);

    LOGGER.info("jsonContent {}", jsonContent);

    // Mock repository behavior
    when(documentRepository.findById(document.getId())).thenReturn(Optional.of(document));

    // Perform GET request and verify response
    mvc.perform(post("/api/v1/documents")
        .content(jsonContent)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }
}
