package com.example.delivery_div.mapper;

import com.example.delivery_div.dto.CustomersDto;
import com.example.delivery_div.dto.UserDto;
import com.example.delivery_div.models.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto entityToUserDto(User user);

    List<UserDto> entityToUserDtoList(List<User> user);

    @InheritInverseConfiguration
    User dtoToEntity(UserDto userDto);

    CustomersDto mapToGetAllCustomer(User user);

    List<CustomersDto> mapToGetAllCustomerList(List<User> user);
}
