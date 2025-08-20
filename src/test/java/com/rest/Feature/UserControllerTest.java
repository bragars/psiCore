package com.rest.Feature;

import com.rest.TestConfig;
import com.rest.endpoint.Controllers.UserController;
import com.rest.entity.User;
import com.rest.repository.IUserDAO;
import com.rest.service.JwtService;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;

@Import(TestConfig.class)
@ExtendWith(MockitoExtension.class)
@WebMvcTest(UserController.class)

public class UserControllerTest {

  @MockBean
  private JwtService jwtService;

  @MockBean
  private IUserDAO iUserDAO;

  @MockBean
  private UserDetails userDetails;

  @Test
  @WithMockUser(username = "user", roles = { "ADMIN" })
  public void shouldCreateUser() throws Exception {

    // Mock Data
    User user = new User("root", "root@root.com", "root");
    user.setId(1L);

    when(iUserDAO.findById(user.getId())).thenReturn(Optional.of(user));
  }
}
