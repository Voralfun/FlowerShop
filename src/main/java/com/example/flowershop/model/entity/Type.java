package com.example.flowershop.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Type {
    ROSE("Роза"),
    CHRYSANTHEMUM("Хризантема"),
    PEONIES("Пионы"),
    ASTER("Астра"),
    CHAMOMILE("Ромашка"),
    GERANIUM("Герань");
    private final String description;
}
