package com.example.delivery_div.mapper;

import com.example.delivery_div.dto.DriverDto;
import com.example.delivery_div.models.Driver;
import org.mapstruct.Mapper;

import java.time.LocalDate;
import java.util.List;

@Mapper(componentModel = "spring")
public interface DriverMapper {

    DriverDto driverToDriverDto(Driver driver);

    List<DriverDto> driverToDriverDtoList(List<Driver> drivers);
}
