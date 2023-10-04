package com.example.delivery_div.mapper;

import com.example.delivery_div.dto.CartDto;
import com.example.delivery_div.models.Cart;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartDto entityToCartDto(Cart cart);
    List<CartDto> entityToCartDtoList(List<Cart> cart);
}
