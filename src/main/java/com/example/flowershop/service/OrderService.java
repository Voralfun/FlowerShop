package com.example.flowershop.service;

import com.example.flowershop.model.dto.CheckOutItemDTO;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

import java.util.List;

public interface OrderService {
    Session createSession(List<CheckOutItemDTO> checkoutItemDtoList) throws StripeException;


}
