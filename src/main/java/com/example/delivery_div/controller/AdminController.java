package com.example.delivery_div.controller;

import com.example.delivery_div.dto.CustomersDto;
import com.example.delivery_div.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/dashboard")
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

//    @GetMapping("/getAllOrders")
//    public ResponseEntity<List<OrdersDto>> getAllOrders() {
//        return ResponseEntity.ok(adminService.getAllOrders());
//    }



}
