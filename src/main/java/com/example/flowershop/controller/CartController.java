package com.example.flowershop.controller;

import com.example.flowershop.model.dto.CartDTO;
import com.example.flowershop.model.dto.ClientDTO;
import com.example.flowershop.service.CartService;
import com.example.flowershop.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class CartController {

    private final CartService cartService;
    public CartController(CartService cartService){
        this.cartService = cartService;
    }
@GetMapping("/cart")
    public String cartInfo(Model model, Principal principal){
        if(principal == null){
            model.addAttribute(" cart", new CartDTO());
        }else{
            CartDTO cartDTO = cartService.getCartByClient(principal.getName());
            model.addAttribute("cart",cartDTO);
        }
        return "cart";
}
    }

