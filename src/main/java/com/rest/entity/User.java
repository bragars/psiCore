package com.rest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.rest.enums.Role;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@Builder
// @JsonSerialize
// @JsonDeserialize
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)

  private @Getter @Setter long id;
  private @Setter String username;
  private @Getter @Setter String email;

  @JsonIgnore
  private @Setter String password;

  @Enumerated(EnumType.STRING)
  private Role role;

  @OneToMany(cascade = CascadeType.ALL)
  private Set<Document> documents;

  public User(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

}
