package com.example.flowershop.model.dto;

import com.example.flowershop.model.entity.Type;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlowerDTO {
    private Long id;
    private String sort;
    private Type type;
    private double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public FlowerDTO() {
    }
}