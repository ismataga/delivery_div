package com.example.delivery_div.dto;


import com.example.delivery_div.annotation.PasswordMatch;
import com.example.delivery_div.annotation.Unique;
import com.example.delivery_div.repository.UserRepository;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@PasswordMatch
public class RegistrationDTO {

    @NotBlank
    @Email
    @Unique(repositoryClass = UserRepository.class, methodName = "existsByUsername")
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String confirmPassword;

}
