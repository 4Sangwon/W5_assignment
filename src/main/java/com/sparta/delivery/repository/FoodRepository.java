package com.sparta.delivery.repository;

import com.sparta.delivery.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findAllByRestaurantId(Long id);

    List<Food> findByRestaurantIdAndFoodName(Long restaurantId, String foodName);
}
