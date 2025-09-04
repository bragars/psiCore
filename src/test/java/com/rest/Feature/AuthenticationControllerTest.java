package com.rest.Feature;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.config.JwtAuthenticationFilter;
import com.rest.dto.request.AuthenticationRequest;
import com.rest.dto.request.RegisterRequest;
import com.rest.dto.response.AuthenticationResponse;
import com.rest.endpoint.controllers.AuthenticationController;
import com.rest.endpoint.services.AuthenticationService;

@WebMvcTest(controllers = AuthenticationController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class AuthenticationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthenticationService authService;

    @MockBean
    private JwtAuthenticationFilter jwtaAuthenticationFilter;

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationControllerTest.class);

    @Test
    public void shouldAuthenticateAndReturnToken() throws Exception {
        System.out.println("--------------------- O TESTE COMEÇOU A SER EXECUTADO! ---------------------");
        AuthenticationRequest authRequest = new AuthenticationRequest("root", "123");
        AuthenticationResponse expectedResponse = new AuthenticationResponse("mock-jwt-token-123");
        when(authService.authenticate(any(AuthenticationRequest.class))).thenReturn(expectedResponse);

        logger.info("--------------------- Info message ---------------------",
                objectMapper.writeValueAsString(authRequest));
        System.out.println("--------------------- O TESTE COMEÇOU A SER EXECUTADO! ---------------------");

        mockMvc.perform(post("/api/v1/auth/authenticate").with(csrf()).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(authRequest))).andDo(print()) // <-- ADICIONE ESTA LINHA
                .andExpect(status().isOk());
                // .andExpect(jsonPath("$.token").value("mock-jwt-token-123"));
    }

    @Test
    public void shouldRegister() throws Exception {
        AuthenticationResponse expectedResponse = new AuthenticationResponse("mock-jwt-token-123");
        RegisterRequest registerRequest = new RegisterRequest("root", "root@root.com", "123");

        when(authService.register(registerRequest)).thenReturn(expectedResponse);
        logger.info("Info message", objectMapper.writeValueAsString(registerRequest));
        mockMvc.perform(post("/api/v1/auth/register").with(csrf()).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerRequest))).andExpect(status().isOk());
    }
}

// .andExpect(jsonPath("$.token").doesNotExist());
