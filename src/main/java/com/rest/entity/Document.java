package com.rest.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "documents")
public class Document implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)

  private long id;
  private @Getter @Setter String name;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id")
  private User user;
}
