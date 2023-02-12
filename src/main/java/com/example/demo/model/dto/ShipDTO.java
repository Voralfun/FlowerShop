package com.example.demo.model.dto;

import com.example.demo.model.enums.Type;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class ShipDTO {
    String name;
    Type type;
    Float length;
    String serialNUM;
}
