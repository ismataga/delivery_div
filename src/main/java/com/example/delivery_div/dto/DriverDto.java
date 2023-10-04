package com.example.delivery_div.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DriverDto {
    private LocalDate creatDate;
    private UserDto user;
}
