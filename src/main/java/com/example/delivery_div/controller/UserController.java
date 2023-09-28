package com.example.delivery_div.controller;



import com.example.delivery_div.models.User;
import com.example.delivery_div.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{email}")
    public ResponseEntity<User> getUserBYEmail(@PathVariable(name = "email") String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }
}
