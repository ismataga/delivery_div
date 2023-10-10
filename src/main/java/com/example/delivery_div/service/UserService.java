package com.example.delivery_div.service;

import com.example.delivery_div.dto.CartDto;
import com.example.delivery_div.dto.UserDto;
import com.example.delivery_div.exception.UserNotFoundException;
import com.example.delivery_div.mapper.CartMapper;
import com.example.delivery_div.mapper.UserMapper;
import com.example.delivery_div.models.Cart;
import com.example.delivery_div.models.User;
import com.example.delivery_div.repository.CartRepository;
import com.example.delivery_div.repository.UserRepository;
import jakarta.persistence.Column;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final UserMapper userMapper;
    private final CartMapper cartMapper;

//    public User getUserByEmail(String email) {
//        log.info("getUserByEmail().start");
//        return userRepository.findByEmail(email).get();
//    }

    public List<UserDto> getAllUsers() {
        log.info("getAllUsers().start");
        List<User> users = userRepository.findAll();
        List<UserDto> userDto = userMapper.entityToUserDtoList(users);
        log.info("getAllUsers().end");
        return userDto;
    }

    public UserDto getUserById(Long id) {
        log.info("getUserById().start " + id);
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found " + id));
        log.info("getUserById() user " + user);
        log.info("getUserById().end " + id);
        UserDto dto = userMapper.entityToUserDto(user);
        log.info("getUserById() dto " + dto.getName() + " " + dto.getBirthdate() + " " + dto.getSurname());
        return dto;
    }

    public UserDto createUser(UserDto userDto) {
        log.info("createUser().start");
        User user = userMapper.dtoToEntity(userDto);
        user = userRepository.save(user);
        log.info("createUser().end");
        return userMapper.entityToUserDto(user);
    }

    public void deleteUserById(Long id) {
        log.info("deleteUserById().start " + id);
        userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found " + id));
        userRepository.deleteById(id);
        log.info("deleteUserById().end " + id);
    }

    public List<CartDto> getAllFood() {
        log.info("getAllFood().start");
        List<Cart> carts = cartRepository.findAll();
        List<CartDto> cartDto = cartMapper.entityToCartDtoList(carts);
        log.info("getAllFood().end");
        return cartDto;
    }

    public CartDto getFoodByCustomerId(Long id) {
        log.info("getFoodByCustomerId().start " + id);
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found " + id));
        log.info("getFoodByCustomerId().end " + id);
        return cartMapper.entityToCartDto(cart);
    }

    public void deleteProductByIdInCart(Long id) {
        log.info("deleteProductByIdInCart().start " + id);
        cartRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found " + id));
        cartRepository.deleteById(id);
        log.info("deleteProductByIdInCart().end " + id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        log.info("loadUserByUsername().start " + username);
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found " + username));
    }

    public void increaseAttemptCount(String username) {
        userRepository.findByUsername(username)
                .ifPresent(user -> {
                    int attemptAccount = user.getAttemptCount();
                    if (attemptAccount > 2) {
                        user.setAccountNonLocked(false);
                    }
                    user.setAttemptCount(user.getAttemptCount() + 1);
                    userRepository.save(user);
                });
    }

    public void resetAttempts(String username) {
        userRepository.findByUsername(username)
                .ifPresent(user -> {
                    user.setAttemptCount(0);
                    userRepository.save(user);
                });
    }
}
