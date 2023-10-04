package com.example.delivery_div.controller;

import com.example.delivery_div.dto.CartDto;
import com.example.delivery_div.dto.DriverDto;
import com.example.delivery_div.dto.OrdersDto;
import com.example.delivery_div.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrdersDto>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdersDto> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateOrderById(@PathVariable Long id, @RequestBody OrdersDto ordersDto) {
        orderService.updateOrderById(id, ordersDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
