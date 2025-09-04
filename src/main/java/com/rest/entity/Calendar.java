package com.rest.entity;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
//@Entity
//@Table(name = "Calendar")
public class Calendar {
  public @Getter @Setter String day;
  public @Getter @Setter String month;
  public @Getter @Setter String year;
  public @Getter @Setter String time;
}
