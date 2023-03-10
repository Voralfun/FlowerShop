package com.example.flowershop.service;

import com.example.flowershop.model.dto.Cart.AddToCartDTO;
import com.example.flowershop.model.dto.Cart.CartDTO;
import com.example.flowershop.model.entity.Client;

public interface CartService {
    void addToCart(AddToCartDTO addToCartDto, Client client);
    CartDTO listCartItems(Client client);
    void deleteCartItem(Long cartItemId, Client client);

}
