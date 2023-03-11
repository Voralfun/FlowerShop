package com.example.flowershop.service;

import com.example.flowershop.model.dto.FlowerDTO;
import com.example.flowershop.model.entity.Client;
import com.example.flowershop.model.entity.WishList;

import java.util.List;

public interface WishListService {
    void createWishlist(WishList wishList);
    List<FlowerDTO> getWishListForClient(Client client);
    void removeFromWishlist(Long wishListItemId, Client client);
}
