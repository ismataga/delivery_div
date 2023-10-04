package com.example.delivery_div.dto;

import com.example.delivery_div.models.Cart;
import com.example.delivery_div.models.Driver;
import jakarta.persistence.OneToOne;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDto {

    private String place;
    private CartDto card;
    private DriverDto driver;
    private boolean status;

}
