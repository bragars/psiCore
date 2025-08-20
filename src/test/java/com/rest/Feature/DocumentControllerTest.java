package com.rest.Feature;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.TestConfig;
import com.rest.dto.request.NewDocumentRequest;
import com.rest.dto.response.DocumentsResponse;
import com.rest.endpoint.Controllers.DocumentController;
import com.rest.entity.Document;
import com.rest.service.DocumentService;
import com.rest.service.JwtService;

@Import(TestConfig.class)
@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = DocumentController.class)

public class DocumentControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private JwtService jwtService;

  @MockBean
  private DocumentService documentService;

  @MockBean
  private UserDetails userDetails;

  @MockBean
  private UserDetailsService userDetailsService;

  @Test
  @WithMockUser(username = "user", roles = { "ADMIN" })
  public void shouldCreateDocument() throws Exception {
    NewDocumentRequest newDocumentRequest = new NewDocumentRequest("name", 1L);

    Document document = new Document();
    document.setId(1L);
    document.setName("Document 1");

    when(documentService.createDocument(any(NewDocumentRequest.class))).thenReturn(document);

    mockMvc.perform(post("/api/v1/documents").with(csrf()).contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(newDocumentRequest))).andExpect(status().isOk());
  }

  @Test
  @WithMockUser(username = "user", roles = { "ADMIN" })
  public void shouldGetAllDocuments() throws Exception {
    DocumentsResponse response = DocumentsResponse.builder().id(1L).name("Document 1").userId(1L).build();

    List<DocumentsResponse> responses = Arrays.asList(response);
    when(documentService.getAllDocuments()).thenReturn(responses);

    mockMvc.perform(get("/api/v1/documents").with(csrf()).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }
}

// // Mock Data
//     Document document = new Document();
//     document.setId(1L);
//     document.setName("Document 1");

//     // Mock Data
//     User user = new User("root", "root@root.com", "root");
//     user.setId(1L);
