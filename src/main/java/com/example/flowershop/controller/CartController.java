package com.example.flowershop.controller;

import com.example.flowershop.common.ApiResponse;
import com.example.flowershop.model.dto.cart.AddToCartDTO;
import com.example.flowershop.model.dto.cart.CartDTO;
import com.example.flowershop.model.entity.Client;
import com.example.flowershop.service.CartService;
import com.example.flowershop.service.impl.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private AuthenticationServiceImpl authenticationServiceImpl;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDTO addToCartDTO,
                                                 @RequestParam("token") String token) {
        authenticationServiceImpl.authenticate(token);

        Client client = authenticationServiceImpl.getClient(token);


        cartService.addToCart(addToCartDTO, client );

        return new ResponseEntity<>(new ApiResponse(true, "Добавлено в корзину"), HttpStatus.CREATED);
    }


    @GetMapping("/")
    public ResponseEntity<CartDTO> getCartItems(@RequestParam("token") String token) {

        authenticationServiceImpl.authenticate(token);

        Client client = authenticationServiceImpl.getClient(token);

        CartDTO cartDto = cartService.listCartItems(client);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") Long itemId,
                                                      @RequestParam("token") String token) {

        // authenticate the token
        authenticationServiceImpl.authenticate(token);

        // find the user
        Client client = authenticationServiceImpl.getClient(token);

        cartService.deleteCartItem(itemId, client);

        return new ResponseEntity<>(new ApiResponse(true, "Предмет был удалён"), HttpStatus.OK);

    }


}