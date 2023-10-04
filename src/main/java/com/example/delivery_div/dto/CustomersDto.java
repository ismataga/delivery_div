package com.example.delivery_div.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustomersDto {
    private String name;
    private String surname;
    private String email;
    private Integer phoneNumber;


}
