package com.example.demo.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Type {
  CONTAINER("Контейнеровоз"),
  TANKER("Танкер"),
  BATTLESHIP("Военный корабль"),
  OFFSHORE("Морские судна");
  private final String  description;
}
