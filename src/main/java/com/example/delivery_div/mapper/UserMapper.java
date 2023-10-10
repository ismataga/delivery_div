package com.example.delivery_div.mapper;

import com.example.delivery_div.dto.CustomersDto;
import com.example.delivery_div.dto.RegistrationDTO;
import com.example.delivery_div.dto.UserDto;
import com.example.delivery_div.models.User;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;
import java.util.UUID;
@Mapper(componentModel = "spring",imports = UUID.class)
@Slf4j
public abstract class UserMapper {

    @Lazy
    @Autowired
    protected PasswordEncoder encoder;

    public abstract UserDto entityToUserDto(User user);

    public abstract List<UserDto> entityToUserDtoList(List<User> user);

    @InheritInverseConfiguration
    public abstract   User dtoToEntity(UserDto userDto);

    public abstract CustomersDto mapToGetAllCustomer(User user);

    public abstract  List<CustomersDto> mapToGetAllCustomerList(List<User> user);

    @Mapping(target = "name", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", constant = "false")
    @Mapping(target = "accountNonExpired", constant = "true")
    @Mapping(target = "accountNonLocked", constant = "true")
    @Mapping(target = "credentialsNonExpired", constant = "true")
    @Mapping(target = "password", expression = "java(encoder.encode(dto.getPassword()))")
    @Mapping(target = "uuid", expression = "java(UUID.randomUUID())")
    public abstract User toEntity(RegistrationDTO dto);
}
