package com.example.flowershop.service.impl;

import com.example.flowershop.exceptions.FlowerServiceException;
import com.example.flowershop.model.dto.cart.AddToCartDTO;
import com.example.flowershop.model.dto.cart.CartDTO;
import com.example.flowershop.model.dto.cart.CartItemDTO;
import com.example.flowershop.model.entity.Cart;
import com.example.flowershop.model.entity.Client;
import com.example.flowershop.model.entity.Flower;
import com.example.flowershop.model.repository.CartRepository;
import com.example.flowershop.service.CartService;
import com.example.flowershop.service.FlowerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    @Autowired
 FlowerService flowerService;
  @Autowired
 CartRepository cartRepository;
    public void addToCart(AddToCartDTO addToCartDto, Client client) {
        Flower flower = flowerService.findById(addToCartDto.getFlowerId());

        Cart cart = new Cart();
        cart.setFlower(flower);
        cart.setClient(client);
        cart.setQuantity(addToCartDto.getQuantity());

        cartRepository.save(cart);

    }
    public CartDTO listCartItems(Client client) {
        List<Cart> cartList = cartRepository.findAllByClientOrderByCreatedDateDesc(client);

        List<CartItemDTO> cartItems = new ArrayList<>();
        double totalCost = 0;
        for (Cart cart: cartList) {
            CartItemDTO cartItemDTO = new CartItemDTO(cart);
            totalCost += cartItemDTO.getQuantity() * cart.getFlower().getPrice();
            cartItems.add(cartItemDTO);
        }

        CartDTO cartDto = new CartDTO();
        cartDto.setTotalCost(totalCost);
        cartDto.setCartItems(cartItems);
        return cartDto;
    }

    public void deleteCartItem(Long cartItemId, Client client) {
        // the item id belongs to user

        Optional<Cart> optionalCart = cartRepository.findById(cartItemId);

        if (optionalCart.isEmpty()) {
            throw new FlowerServiceException("Неверный id: " + cartItemId);
        }

        Cart cart = optionalCart.get();

        if (cart.getClient() != client) {
            throw  new FlowerServiceException("Товар в корзине не принадлежит пользователю: " +cartItemId);
        }

        cartRepository.delete(cart);


    }

}
