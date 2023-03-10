package com.example.flowershop.service.impl;

import com.example.flowershop.model.dto.FlowerDTO;
import com.example.flowershop.model.entity.Client;
import com.example.flowershop.model.entity.WishList;
import com.example.flowershop.model.repository.WishListRepository;
import com.example.flowershop.service.FlowerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishListServiceImpl {
    WishListRepository wishListRepository;
    FlowerService flowerService;
    public void createWishlist(WishList wishList) {
        wishListRepository.save(wishList);
    }

    public List<FlowerDTO> getWishListForUser(Client client) {
        final List<WishList> wishLists = wishListRepository.findAllByClientOrderByCreatedDateDesc(client);
        List<FlowerDTO> flowerDto = new ArrayList<>();
        for (WishList wishList: wishLists) {
            flowerDto.add(flowerService.getFlowerDTO(wishList.getFlower()));
        }

        return flowerDto;
    }
}
