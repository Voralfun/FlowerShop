package com.example.flowershop.service.impl;

import com.example.flowershop.exceptions.CustomException;
import com.example.flowershop.exceptions.FlowerServiceException;
import com.example.flowershop.model.dto.CartDTO;
import com.example.flowershop.model.dto.CartDetailDTO;
import com.example.flowershop.model.entity.Cart;
import com.example.flowershop.model.entity.Client;
import com.example.flowershop.model.entity.Flower;
import com.example.flowershop.model.entity.Status;
import com.example.flowershop.model.repository.CartRepository;
import com.example.flowershop.model.repository.FlowerRepository;
import com.example.flowershop.service.CartService;
import com.example.flowershop.service.ClientService;
import com.example.flowershop.utils.JsonConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Slf4j
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final FlowerRepository flowerRepository;
    private final ClientService clientService;
    @Override
    @Transactional
    public Cart createCart(Client client,List<Long> flowerIds) {
        Cart cart = new Cart();
        cart.setClient(client);
        List<Flower> flowersList = getCollectRefFlowersByIds(flowerIds);
        cart.setFlowers(flowersList);
        return cartRepository.save(cart);
    }

    @Override
    public CartDTO getCartByClient(String email) {
        Client client = clientService.findByEmail(email);
        if(client == null || client.getCart() == null){
            return new CartDTO();
        }
        CartDTO cartDTO = new CartDTO();
        Map<Long, CartDetailDTO> mapByFlowerId = new HashMap<>();

        List<Flower> flowers = client.getCart().getFlowers();
        for(Flower flower : flowers){
            CartDetailDTO detail = mapByFlowerId.get(flower.getId());
            if(detail == null){
                mapByFlowerId.put(flower.getId(),new CartDetailDTO(flower));
            }else{
                detail.setAmount(detail.getAmount().add(new BigDecimal(1.0)));
                detail.setSum(detail.getSum() + Double.valueOf(flower.getPrice().toString()));
            }
        }
        cartDTO.setCartDetails(new ArrayList<>(mapByFlowerId.values()));
        cartDTO.aggregate();
        return cartDTO;
    }

    private List<Flower> getCollectRefFlowersByIds(List<Long> flowerIds){
        return flowerIds.stream()
                .map(flowerRepository::getOne)
                .collect(Collectors.toList());
    }
    @Override
    public void addFlowers(Cart cart, List<Long> flowerIds){
        List<Flower> flowers = cart.getFlowers();
        List<Flower> newFlowerList = flowers == null ? new ArrayList<>() : new ArrayList<>(flowers);
        newFlowerList.addAll(getCollectRefFlowersByIds(flowerIds));
        cart.setFlowers(newFlowerList);
        cartRepository.save(cart);
    }
}
