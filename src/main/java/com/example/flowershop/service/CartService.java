package com.example.flowershop.service;

import com.example.flowershop.model.dto.cart.AddToCartDTO;
import com.example.flowershop.model.dto.cart.CartDTO;
import com.example.flowershop.model.entity.Client;

public interface CartService {
    void addToCart(AddToCartDTO addToCartDto, Client client);
    CartDTO listCartItems(Client client);
    void deleteCartItem(Long cartItemId, Client client);

}
