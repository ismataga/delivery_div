package com.example.delivery_div.repository;


import com.example.delivery_div.models.User;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
