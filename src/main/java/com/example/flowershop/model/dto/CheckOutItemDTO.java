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

    public CheckOutItemDTO() {
    }

    public String getFlowerSort() {
        return flowerSort;
    }

    public void setFlowerSort(String flowerSort) {
        this.flowerSort = flowerSort;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getFlowerId() {
        return flowerId;
    }

    public void setFlowerId(long flowerId) {
        this.flowerId = flowerId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
}
