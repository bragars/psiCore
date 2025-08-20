package com.rest.endpoint.Controllers;

import com.rest.entity.Document;
import com.rest.entity.User;
import com.rest.service.UserService; // Importa o novo serviço
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService; // A única dependência agora é o serviço

  @GetMapping("/")
  String Home() {
    return "read docs";
  }

  @GetMapping("/users")
  public ResponseEntity<List<User>> getAllUsers() {
    List<User> users = userService.getAllUsers();
    return ResponseEntity.ok(users);
  }

  @GetMapping("/users/{id}")
  public ResponseEntity<User> getUserById(@PathVariable Long id) {
    User user = userService.getUserById(id);
    return ResponseEntity.ok(user);
  }

  @GetMapping("/users/{id}/documents")
  public ResponseEntity<Set<Document>> getUserDocuments(@PathVariable Long id) {
    Set<Document> documents = userService.getUserDocuments(id);
    return ResponseEntity.ok(documents);
  }

  @PutMapping("/users/{id}")
  public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id) {
    User updatedUser = userService.updateUser(id, user);
    return ResponseEntity.ok(updatedUser);
  }

  @DeleteMapping("/users/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
    // Retorna 204 No Content, que é a resposta padrão para um DELETE bem-sucedido
    return ResponseEntity.noContent().build();
  }
}

// @GetMapping("/authorities")
// public Map<String, Object> getPrincipalInfo(JwtAuthenticationToken principal)
// {

// Collection<String> authorities =
// principal.getAuthorities().stream().map(GrantedAuthority::getAuthority)
// .collect(Collectors.toList());

// Map<String, Object> info = new HashMap<>();
// info.put("name", principal.getName());
// info.put("authorities", authorities);
// info.put("tokenAttributes", principal.getTokenAttributes());

// if (principal instanceof AccountToken) {
// info.put("account", ((AccountToken) principal).getAccount());
// }

// return info;
// }
