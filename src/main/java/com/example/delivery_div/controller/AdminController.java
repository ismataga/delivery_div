package com.example.delivery_div.controller;

import com.example.delivery_div.dto.CustomersDto;
import com.example.delivery_div.service.AdminService;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/dashboard")
@Slf4j
public class AdminController {
    private final AdminService adminService;

//    3.	GET - /admin/dashboard – admin bura müraciət edərək, aşağıda qeyd etdiyim statistikaları izləyə bilməlidir.
//    (Bu məlumatları bazada saxlamırsız. Sadəcə baza da var olan datalar üzərində sorğular yazıb, onlar üzərində
//    hesablama aparırsız.)
//Toplam customer sayı
//Toplam driver sayı
//Günlük – gəlir, sifariş sayı
//Aylıq – gəlir, sifariş sayı
//İllik – gəlir, sifariş sayı

    @GetMapping("/customers")
//    @PreAuthorize("ROLE_CUSTOMER")
    public ResponseEntity<List<CustomersDto>> getAllCustomers() {
        return ResponseEntity.ok(adminService.getAllCustomer());
    }

    @GetMapping("/customers/count")
    public ResponseEntity<Integer> getCustomersCount() {
        return  ResponseEntity.ok(adminService.getCustomersCount());
    }

    @GetMapping("/drivers/count")
    public ResponseEntity<Integer> getDriversCount() {
        return  ResponseEntity.ok(adminService.getDriversCount());
    }

    @GetMapping("/orders/count")
    public ResponseEntity<Integer> getOrdersCount() {
        return  ResponseEntity.ok(adminService.getOrdersCount());
    }


    @GetMapping("/orders/sum/{createdDate}")
    public ResponseEntity<Integer> getOrdersDailySum(@PathVariable @JsonFormat(pattern = "yyyy-MM-dd") LocalDate createdDate) {
        return  ResponseEntity.ok( adminService.getOrdersDailySum(createdDate));
    }

//    @GetMapping("/orders/sum/{createdDate}/{updatedDate}")
//    public ResponseEntity<Integer> getOrdersMonthlySum(@PathVariable @JsonFormat(pattern = "yyyy-MM-dd") LocalDate createdDate, @PathVariable @JsonFormat(pattern = "yyyy-MM-dd") LocalDate updatedDate) {
//        return  ResponseEntity.ok( adminService.getOrdersMonthlySum(createdDate, updatedDate));
//    }
//    @GetMapping("/orders/sum/{createdDate}/{updatedDate}")
//    public ResponseEntity<Integer> getOrdersMonthlySum(
//            @PathVariable("createdDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate createdDate,
//            @PathVariable("updatedDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate updatedDate) {
//
//        Integer monthlySum = adminService.getOrdersMonthlySum(createdDate, updatedDate);
//        return ResponseEntity.ok(monthlySum);
//    }
    @GetMapping("/orders/sum")
    public ResponseEntity<Integer> getOrdersMonthlySum(
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {

        Integer monthlySum = adminService.getOrdersMonthlySum(startDate, endDate);
        return ResponseEntity.ok(monthlySum);
    }



}
