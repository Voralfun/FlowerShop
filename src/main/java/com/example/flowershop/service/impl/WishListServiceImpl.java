package com.example.flowershop.service.impl;

import com.example.flowershop.exceptions.FlowerServiceException;
import com.example.flowershop.model.dto.FlowerDTO;
import com.example.flowershop.model.entity.Cart;
import com.example.flowershop.model.entity.Client;
import com.example.flowershop.model.entity.WishList;
import com.example.flowershop.model.repository.WishListRepository;
import com.example.flowershop.service.FlowerService;
import com.example.flowershop.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WishListServiceImpl implements WishListService {
    @Autowired
    WishListRepository wishListRepository;
    @Autowired
    FlowerService flowerService;
    public void createWishlist(WishList wishList) {
        wishListRepository.save(wishList);
    }

    public List<FlowerDTO> getWishListForClient(Client client) {
        final List<WishList> wishLists = wishListRepository.findAllByClientOrderByCreatedDateDesc(client);
        List<FlowerDTO> flowerDto = new ArrayList<>();
        for (WishList wishList: wishLists) {
            flowerDto.add(flowerService.getFlowerDTO(wishList.getFlower()));
        }

        return flowerDto;
    }
    public void removeFromWishlist(Long wishListItemId, Client client) {


        Optional<WishList> optionalWishList = wishListRepository.findById(wishListItemId);

        if (optionalWishList.isEmpty()) {
            throw new FlowerServiceException("Неверный id: " + wishListItemId);
        }

        WishList wishList = optionalWishList.get();

        if (wishList.getClient() != client) {
            throw  new FlowerServiceException("Товар в списке желаемого не принадлежит пользователю: " +wishListItemId);
        }
        wishListRepository.delete(wishList);

    }
}
