package com.example.flowershop.model.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;


import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@Getter
@Setter

@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartDTO {
    private int amountFlowers;
    private Double sum;
    private List<CartDetailDTO> cartDetails= new ArrayList<>();
    public void aggregate(){
        this.amountFlowers = cartDetails.size();
        this.sum = cartDetails.stream()
                .map(CartDetailDTO::getSum)
                .mapToDouble(Double::doubleValue)
                .sum();
    }
}
