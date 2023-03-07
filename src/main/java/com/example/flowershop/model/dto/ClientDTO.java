package com.example.flowershop.model.dto;

import com.example.flowershop.model.entity.Cart;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientDTO {
    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private String email;
    private LocalDate birthdate;
    private Integer phoneNUM;
    private Cart cart;
}
