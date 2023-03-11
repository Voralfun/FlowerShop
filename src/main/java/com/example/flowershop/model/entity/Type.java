package com.example.flowershop.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum Type {
    ROSE("Роза"),
    CHRYSANTHEMUM("Хризантема"),
    PEONIES("Пионы"),
    ASTER("Астра"),
    CHAMOMILE("Ромашка"),
    GERANIUM("Герань");

    public String getFlowerType() {
        return flowerType;
    }

    Type(String flowerType) {
        this.flowerType = flowerType;
    }

    private final String flowerType;

}
