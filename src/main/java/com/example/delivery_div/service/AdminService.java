package com.example.delivery_div.service;

import com.example.delivery_div.dto.CustomersDto;
import com.example.delivery_div.mapper.UserMapper;
import com.example.delivery_div.models.User;
import com.example.delivery_div.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<CustomersDto> getAllCustomer() {
        log.info("getAllCustomers().start");
        List<User> users = userRepository.getAllCustomers();
        List<CustomersDto> getAllCustomers = userMapper.mapToGetAllCustomerList(users);
        log.info("getAllCustomers().end");
        return getAllCustomers;
    }


}
