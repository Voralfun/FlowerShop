package com.example.demo.model.dto;

import com.example.demo.model.entity.Crew;
import com.example.demo.model.enums.Type;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ShipDTO {
    String name;
    Type type;
    float length;

    List<CrewDTO> crews;
}
