package com.example.delivery_div.mapper;

import com.example.delivery_div.dto.FoodDto;
import com.example.delivery_div.models.Food;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FoodMapper {
    FoodDto entityToFoodDto(Food food);
    List<FoodDto> entityToFoodDtoList(List<Food> food);
    Food foodDtoToEntity(FoodDto foodDto);
}
