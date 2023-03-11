package com.example.flowershop.service.impl;

import com.example.flowershop.model.dto.CheckOutItemDTO;
import com.example.flowershop.service.OrderService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Value("${BASE_URL}")
    private String baseURL;

    @Value("${STRIPE_SECRET_KEY}")
    private String apiKey;

    public Session createSession(List<CheckOutItemDTO> checkoutItemDtoList) throws StripeException {

        // sucess and failure urls

        String successURL = baseURL + "Payment/success";

        String failureURL = baseURL + "Payment/cancelled";

        Stripe.apiKey = apiKey;

        List<SessionCreateParams.LineItem> sessionItemList = new ArrayList<>();


        for (CheckOutItemDTO checkOutItemDTO: checkoutItemDtoList) {
            sessionItemList.add(createSessionLineItem(checkOutItemDTO));
        }

        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setCancelUrl(failureURL)
                .addAllLineItem(sessionItemList)
                .setSuccessUrl(successURL)
                .build();
        return Session.create(params);
    }

    private SessionCreateParams.LineItem createSessionLineItem(CheckOutItemDTO сheckOutItemDTO) {

        return SessionCreateParams.LineItem.builder()
                .setPriceData(createPriceData(сheckOutItemDTO))
                .setQuantity(Long.parseLong(String.valueOf(сheckOutItemDTO.getQuantity())))
                .build();

    }

    private SessionCreateParams.LineItem.PriceData createPriceData(CheckOutItemDTO checkoutItemDto) {
        return SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("usd")
                .setUnitAmount((long)(checkoutItemDto.getPrice()*100))
                .setProductData(
                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                .setName(checkoutItemDto.getFlowerSort())
                                .build()
                ).build();
    }
}