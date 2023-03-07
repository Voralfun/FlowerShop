package com.example.flowershop.model.dto;

import com.example.flowershop.model.entity.Flower;
import com.example.flowershop.model.entity.Type;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartDetailDTO {
    private Long flowerId;
    private BigDecimal price;
    private BigDecimal amount;
    private Type type;
    private String sort;
    private Double sum;
    public CartDetailDTO(Flower flower){
        this.flowerId = flower.getId();
        this.type = flower.getType();
        this.sort = flower.getSort();
        this.price = flower.getPrice();
        this.amount = new BigDecimal(1.0);
        this.sum = Double.valueOf(flower.getPrice().toString());
    }
}
