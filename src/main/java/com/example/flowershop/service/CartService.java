package com.example.flowershop.service;

import com.example.flowershop.model.dto.CartDTO;
import com.example.flowershop.model.entity.Cart;
import com.example.flowershop.model.entity.Client;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CartService {
    void addFlowers(Cart cart, List<Long> flowerIds);

    Cart createCart(Client client, List<Long> flowerIds);

    CartDTO getCartByClient(String email);

}
