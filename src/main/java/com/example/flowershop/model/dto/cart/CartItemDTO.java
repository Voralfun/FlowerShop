package com.example.flowershop.model.dto.cart;

import com.example.flowershop.model.entity.Cart;
import com.example.flowershop.model.entity.Flower;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class CartItemDTO {
    private Long id;
    private Integer quantity;
    private Flower flower;



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

    public CartItemDTO() {
    }
    public CartItemDTO(Cart cart) {
        this.id = cart.getId();
        this.quantity = cart.getQuantity();
        this.setFlower(cart.getFlower());
    }
}
