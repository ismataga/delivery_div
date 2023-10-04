package com.example.delivery_div.service;

import com.example.delivery_div.dto.DriverDto;
import com.example.delivery_div.dto.OrdersDto;
import com.example.delivery_div.exception.UserNotFoundException;
import com.example.delivery_div.mapper.OrderMapper;
import com.example.delivery_div.models.Driver;
import com.example.delivery_div.models.Order;
import com.example.delivery_div.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public List<OrdersDto> getAllOrders() {
        log.info("getAllOrders().start");
        List<Order> orders = orderRepository.findAll();
        List<OrdersDto> ordersDto = orderMapper.orderToOrdersDtoList(orders);
        log.info("getAllOrders().end");
        return ordersDto;
    }

    public OrdersDto getOrderById(Long id) {
        log.info("getOrderById().start " + id);
        Order order = orderRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found " + id));
        OrdersDto ordersDto = orderMapper.orderToOrdersDto(order);
        log.info("getOrderById().end " + id);
        return ordersDto;
    }


    public void updateOrderById(Long id, OrdersDto ordersDto) {
        log.info("updateOrderById().start " + id);
        Order order = orderRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found " + id));
        if (!ordersDto.isStatus()) {
            order.setStatus(ordersDto.isStatus());
            orderRepository.save(order);
        }
        log.info("updateOrderById().end " + id);
    }
}
