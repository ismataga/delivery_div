package com.example.delivery_div.dto;

import com.example.delivery_div.models.Cart;
import com.example.delivery_div.models.Category;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FoodDto {
    private String name;
    private String description;
    private int amount;
    private String foodDetails;
    private String image;
    private CategoryDto category;



}
