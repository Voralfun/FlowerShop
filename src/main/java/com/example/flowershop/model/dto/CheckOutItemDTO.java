package com.example.flowershop.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckOutItemDTO {
    private String flowerSort;
    private int quantity;
    private double price;
    private long flowerId;
    private int clientId;
}
