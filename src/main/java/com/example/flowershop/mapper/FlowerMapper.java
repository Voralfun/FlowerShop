package com.example.flowershop.mapper;

import com.example.flowershop.model.dto.FlowerDTO;
import com.example.flowershop.model.entity.Flower;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface FlowerMapper {
    FlowerMapper MAPPER = Mappers.getMapper(FlowerMapper.class);
    Flower toFlower(FlowerDTO dto);
    @InheritInverseConfiguration
    FlowerDTO fromFlower(Flower flower);
    List<Flower> toFlowerList(List<FlowerDTO> flowerDTOS);
    List<FlowerDTO> fromFlowerList(List<Flower> flowers);
}
