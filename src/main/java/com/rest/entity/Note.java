package com.rest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

//@Entity
//@Table(name="note")
public class Note {
  public @Getter @Setter String goals;
  public @Getter @Setter String description;
}
