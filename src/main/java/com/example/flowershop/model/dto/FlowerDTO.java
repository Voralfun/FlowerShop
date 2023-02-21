package com.example.flowershop.model.dto;

import com.example.flowershop.model.entity.Type;
import lombok.Data;
@Data
public class FlowerDTO {
    private Long id;
    private String sort;
    private Type type;
    private Double price;
}