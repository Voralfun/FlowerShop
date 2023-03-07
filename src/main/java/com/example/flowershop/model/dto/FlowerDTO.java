package com.example.flowershop.model.dto;

import com.example.flowershop.model.entity.Type;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlowerDTO {
    private Long id;
    private String sort;
    private Type type;
    private BigDecimal price;
}