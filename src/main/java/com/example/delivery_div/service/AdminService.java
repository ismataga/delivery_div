package com.example.delivery_div.service;

import com.example.delivery_div.dto.CustomersDto;
import com.example.delivery_div.mapper.UserMapper;
import com.example.delivery_div.models.User;
import com.example.delivery_div.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public Integer getCustomersCount() {
        log.info("getCustomersCount().start");
        Integer users = userRepository.getCustomersCount();
        log.info("getCustomersCount().end");
        return users;
    }

    public Integer getDriversCount() {
        log.info("getDriversCount().start");
        Integer driversCount = userRepository.getDriversCount();
        log.info("getDriversCount().end");
        return driversCount;
    }


    public Integer getOrdersCount() {
        log.info("getOrdersCount().start");
        Integer getOrdersCount = userRepository.getOrdersCount();
        log.info("getOrdersCount().end");
        return getOrdersCount;
    }

    public Integer getOrdersDailySum(LocalDate createdDate) {
        log.info("getOrdersDailySum().start");
        Integer getOrdersSum = userRepository.getOrdersDailySum(createdDate);
        log.info("getOrdersDailySum().end " + getOrdersSum);
        return getOrdersSum;
    }
    public Integer getOrdersMonthlySum(LocalDate createdDate,LocalDate updatedDate) {
        log.info("getOrdersMonthlySum().start");
        Integer getOrdersSum = userRepository.getOrdersMonthlySum(createdDate,updatedDate);
        log.info("getOrdersMonthlySum().end " + getOrdersSum);
        return getOrdersSum;
    }



}
