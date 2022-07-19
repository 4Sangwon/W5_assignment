package com.sparta.delivery.service;

import com.sparta.delivery.domain.Food;
import com.sparta.delivery.dto.FoodRequestDto;
import com.sparta.delivery.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;
    public List<Food> getFoods(Long restaurantId) {
        return foodRepository.findAllByRestaurantId(restaurantId);
    }

    @Transactional
    public ResponseEntity<String> saveFood(Long restaurantId, List<FoodRequestDto> requestDto) {
        for (FoodRequestDto f : requestDto) {
            if(!chkFoodName(restaurantId, f.getFoodName()))
                return ResponseEntity.badRequest().body("이미 등록된 음식입니다.");
            if(!chkFoodPrice(f.getFoodPrice()))
                return ResponseEntity.badRequest().body("음식 값은 100원 단위로만 등록 가능합니다");
            else {
                Food food = new Food(restaurantId, f.getFoodName(), f.getFoodPrice());
                foodRepository.save(food);
            }
        }
        return ResponseEntity.ok("정상 등록 되었습니다");
    }

    private boolean chkFoodName(Long restaurantId, String foodName) {
        List<Food> foodList = foodRepository.findByRestaurantIdAndFoodName(restaurantId, foodName);
        if(foodList.size() != 0) {
            return false;
        } else return true;
    }

    private boolean chkFoodPrice(int foodPrice) {
        if (foodPrice % 100 != 0) {
            return false;
        }
        else return true;
    }
}
