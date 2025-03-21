package com.rest;

import com.rest.config.JwtAuthenticationFilter;
import com.rest.service.JwtService;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetailsService;

@TestConfiguration
@Profile("test")
public class TestConfig {

  @Primary
  public JwtService jwtService() {
    // Create a mock JwtService for testing
    return Mockito.mock(JwtService.class);
  }

  @Primary
  public UserDetailsService userDetailsService() {
    // Create a mock UserDetailsService for testing
    return Mockito.mock(UserDetailsService.class);
  }

  @Primary
  public JwtAuthenticationFilter jwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
    // Create a mock filter or one with the mock service
    return new JwtAuthenticationFilter(jwtService, userDetailsService);
  }
}
