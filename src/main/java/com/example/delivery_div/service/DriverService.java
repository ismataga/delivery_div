package com.example.delivery_div.service;

import com.example.delivery_div.dto.DriverDto;
import com.example.delivery_div.exception.UserNotFoundException;
import com.example.delivery_div.mapper.DriverMapper;
import com.example.delivery_div.models.Driver;
import com.example.delivery_div.models.User;
import com.example.delivery_div.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DriverService {
    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;

    public List<DriverDto> getAllDriver() {
        log.info("getAllDriver().start");
        List<Driver> drivers = driverRepository.findAll();
        List<DriverDto> driverDto = driverMapper.driverToDriverDtoList(drivers);
        log.info("getAllDriver().end");
        return driverDto;

    }

    public DriverDto getDriverById(Long id) {
        log.info("getDriver().start");
        Driver driver = driverRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found " + id));
        DriverDto driverDto = driverMapper.driverToDriverDto(driver);
        log.info("getDriver().end");
        return driverDto;
    }

    public void updateDriverById(Long id, DriverDto driverDto) {
        log.info("updateDriverById().start");
        Driver driver = driverRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found " + id));
        if (driverDto != null) {
            driver.setCreatDate(driverDto.getCreatDate());
            driverRepository.save(driver);
//            driver.setUser(driverDto.getUser().getName());
        }
        log.info("updateDriverById().end");
    }

    public void deleteDriverById(Long id) {
        log.info("updateDriverById().start " + id);
        driverRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found " + id));
        driverRepository.deleteById(id);
        log.info("updateDriverById().end " + id);
    }
}
