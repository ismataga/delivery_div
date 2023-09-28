package com.example.delivery_div.service;

import com.example.delivery_div.models.User;
import com.example.delivery_div.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).get();

    }
}
