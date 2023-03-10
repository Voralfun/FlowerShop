package com.example.flowershop.model.dto.Cart;

import com.example.flowershop.model.entity.Cart;
import com.example.flowershop.model.entity.Flower;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
public class CartItemDTO {
    private Long id;
    private Integer quantity;
    private Flower flower;

    public CartItemDTO(Long id, Integer quantity, Flower flower) {
        this.id = id;
        this.quantity = quantity;
        this.flower = flower;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Flower getFlower() {
        return flower;
    }

    public void setFlower(Flower flower) {
        this.flower = flower;
    }

    public CartItemDTO(Cart cart) {
    }
}
