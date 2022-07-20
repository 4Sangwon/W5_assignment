package com.sparta.delivery.controller;

import com.sparta.delivery.domain.Food;
import com.sparta.delivery.dto.FoodRequestDto;
import com.sparta.delivery.service.FoodService;
import com.sparta.delivery.validator.CustomValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class FoodController {
    private final FoodService foodService;
    private final CustomValidator customValidator;

    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> getFoods(@PathVariable Long restaurantId) {
        return foodService.getFoods(restaurantId);
    }

    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void registerFood(@PathVariable Long restaurantId, @RequestBody @Valid List<FoodRequestDto> requestDto, Errors errors) {
        customValidator.validate(requestDto, errors);
        if(errors.hasErrors()) {
            throw new IllegalArgumentException("dd");
        }
        foodService.saveFood(restaurantId, requestDto);
    }
}
