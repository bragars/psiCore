package com.rest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
//@Entity
//@Table(name = "session")
public class Session {
  public @Getter @Setter String name;
  public @Getter @Setter String description;
}
