package com.example.delivery_div.service;

import com.example.delivery_div.dto.DriverDto;
import com.example.delivery_div.dto.FoodDto;
import com.example.delivery_div.dto.UserDto;
import com.example.delivery_div.exception.UserNotFoundException;
import com.example.delivery_div.mapper.FoodMapper;
import com.example.delivery_div.models.Driver;
import com.example.delivery_div.models.Food;
import com.example.delivery_div.models.User;
import com.example.delivery_div.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FoodService {
    private final FoodRepository foodRepository;
    private final FoodMapper foodMapper;

    public List<FoodDto> getAllFoods() {
        log.info("getAllFoods().start");
        List<Food> foods = foodRepository.findAll();
        List<FoodDto> foodDto = foodMapper.entityToFoodDtoList(foods);
        log.info("getAllFoods().end");
        return foodDto;

    }

    public FoodDto getFoodById(Long id) {
        log.info("getFoodById().start " + id);
        Food foods = foodRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found " + id));
        FoodDto foodDto = foodMapper.entityToFoodDto(foods);
        log.info("getFoodById().end " + id);
        return foodDto;
    }

    public void updateFoodById(Long id, FoodDto foodDto) {
        log.info("updateFoodById().start " + id);
        Food foods = foodRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found " + id));
        if (foodDto != null) {
            foods.setFoodDetails(foodDto.getFoodDetails());
            foodRepository.save(foods);
        }
        log.info("updateFoodById().end " + id);
    }

    public void deleteFoodById(Long id) {
        log.info("updateDriverById().start " + id);
        foodRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found " + id));
        foodRepository.deleteById(id);
        log.info("updateDriverById().end " + id);
    }

    public FoodDto createFood(FoodDto foodDto) {
        log.info("createFood().start");
        Food food = foodMapper.foodDtoToEntity(foodDto);
        food = foodRepository.save(food);
        log.info("createFood().end");
        return foodMapper.entityToFoodDto(food);
    }
}
