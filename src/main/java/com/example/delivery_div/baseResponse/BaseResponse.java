package com.example.delivery_div.baseResponse;

import lombok.*;
import org.springframework.http.HttpStatus;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseResponse<T> {
    String message;
    HttpStatus httpStatus;
    T data;
}
