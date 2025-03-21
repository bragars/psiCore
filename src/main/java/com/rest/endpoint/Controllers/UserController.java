package com.rest.endpoint.Controllers;

import com.rest.exception.UserNotFoundException;
import com.rest.entity.Document;
import com.rest.entity.User;
import com.rest.repository.IUserDAO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RequestMapping("/api/v1")
@RestController
public class UserController {
  private final IUserDAO repository;

  UserController(IUserDAO repository) {
    this.repository = repository;
  }

  @GetMapping("/")
  String Home() {
    return "read docs";
  }

  @GetMapping("/users")
  List<User> all() {
    return repository.findAll();
  }

  @GetMapping("/users/{id}")
  User getById(@PathVariable Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new UserNotFoundException(id));
  }

  @GetMapping("/users/{id}/documents")
  Set<Document> getDocuments(@PathVariable Long id) {
    var user = repository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(id));
    return user.getDocuments();
  }

  @PutMapping("/users/{id}")
  User replaceUser(@RequestBody User newUser, @PathVariable Long id) {

    return repository.findById(id)
        .map(user -> {
          user.setUsername(newUser.getUsername());
          user.setPassword(newUser.getPassword());
          return repository.save(user);
        })
        .orElseGet(() -> {
          newUser.setId(21);
          return repository.save(newUser);
        });
  }

  @DeleteMapping("/users/{id}")
  void deleteUser(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
