package com.example.delivery_div.mapper;

import com.example.delivery_div.dto.OrdersDto;
import com.example.delivery_div.models.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrdersDto orderToOrdersDto(Order order);

    List<OrdersDto> orderToOrdersDtoList(List<Order> order);
}
