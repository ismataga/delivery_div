package com.example.delivery_div.dto;

import com.example.delivery_div.models.Food;
import com.example.delivery_div.models.User;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    private byte count;
    private double totalAmount;
    private List<FoodDto> foods;
    private UserDto user;


}
