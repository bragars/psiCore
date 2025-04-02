package com.rest.Integration;

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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.when;

@Import(TestConfig.class)
@ExtendWith(MockitoExtension.class)
@WebMvcTest(DocumentController.class)

public class DocumentControllerTest {

  @MockBean
  private JwtService jwtService;

  @MockBean
  private IDocumentDAO documentRepository;

  @MockBean
  private IUserDAO iUserDAO;

  @MockBean
  private UserDetails userDetails;

  @Test
  @WithMockUser(username = "user", roles = { "ADMIN" })
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

    // LOGGER.info("jsonContent {}", jsonContent);

    // Mock repository behavior
    when(documentRepository.findById(document.getId())).thenReturn(Optional.of(document));

    // Perform GET request and verify response
    // mvc.perform(post("/api/v1/documents")
    // .content(jsonContent)
    // .contentType(MediaType.APPLICATION_JSON))
    // .andExpect(status().isOk());
  }
}
