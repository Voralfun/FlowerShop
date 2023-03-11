package com.example.flowershop.controller;

import com.example.flowershop.common.ApiResponse;
import com.example.flowershop.model.dto.FlowerDTO;
import com.example.flowershop.model.entity.Client;
import com.example.flowershop.model.entity.Flower;
import com.example.flowershop.model.entity.WishList;
import com.example.flowershop.service.AuthenticationService;
import com.example.flowershop.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishListController {

    @Autowired
    WishListService wishListService;

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToWishList(@RequestBody Flower flower,
                                                     @RequestParam("token") String token) {
        authenticationService.authenticate(token);

        Client client = authenticationService.getClient(token);
        WishList wishList = new WishList(client, flower);

        wishListService.createWishlist(wishList);

        ApiResponse apiResponse = new ApiResponse(true, "Added to wishlist");
        return  new ResponseEntity<>(apiResponse, HttpStatus.CREATED);

    }

    @GetMapping("/{token}")
    public ResponseEntity<List<FlowerDTO>> getWishList(@PathVariable("token") String token) {

        authenticationService.authenticate(token);
        Client client = authenticationService.getClient(token);

        List<FlowerDTO> flowerDto = wishListService.getWishListForClient(client);

        return new ResponseEntity<>(flowerDto, HttpStatus.OK);

    }

    @DeleteMapping("/delete/{wishlistItemId}")
    public ResponseEntity<ApiResponse> removeFromWishlist(@PathVariable("wishlistItemId") Long itemId,
                                                      @RequestParam("token") String token) {
        authenticationService.authenticate(token);

        Client client = authenticationService.getClient(token);

        wishListService.removeFromWishlist(itemId, client);

        return new ResponseEntity<>(new ApiResponse(true, "Предмет был удалён из списка желаний"), HttpStatus.OK);

    }

}