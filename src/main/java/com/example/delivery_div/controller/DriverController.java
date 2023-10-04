package com.example.delivery_div.controller;

import com.example.delivery_div.dto.DriverDto;
import com.example.delivery_div.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/drivers")
public class DriverController {
    private final DriverService driverService;
    @GetMapping
    public ResponseEntity<List<DriverDto>>getAllDriver() {
        return ResponseEntity.ok(driverService.getAllDriver());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverDto> getDriverById(@PathVariable Long id) {
        return ResponseEntity.ok(driverService.getDriverById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateDriverById(@PathVariable Long id, @RequestBody DriverDto driverDto) {
        driverService.updateDriverById(id, driverDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDriverById(@PathVariable Long id) {
        driverService.deleteDriverById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
