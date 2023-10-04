package com.example.delivery_div.controller;



import com.example.delivery_div.dto.CartDto;
import com.example.delivery_div.dto.UserDto;
import com.example.delivery_div.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class CustomerController {

    private final UserService userService;

//    @GetMapping("/{email}")
//    public ResponseEntity<User> getUserByEmail(@PathVariable(name = "email") String email) {
//        return ResponseEntity.ok(userService.getUserByEmail(email));
//    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.createUser(userDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/cart")
    public ResponseEntity<List<CartDto>> getAllFoodInCart() {
        return ResponseEntity.ok(userService.getAllFood());
    }
    @GetMapping("/cart/{id}")
    public ResponseEntity<CartDto> getFoodByCustomerIdInCart(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(userService.getFoodByCustomerId(id));
    }

    @DeleteMapping("/cart/{id}")
    public ResponseEntity deleteProductByIdInCart(@PathVariable Long id) {
        userService.deleteProductByIdInCart(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
