package com.sparta.delivery.service;

import com.sparta.delivery.domain.Food;
import com.sparta.delivery.domain.Restaurant;
import com.sparta.delivery.dto.FoodRequestDto;
import com.sparta.delivery.repository.FoodRepository;
import com.sparta.delivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public List<Food> getFoods(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow( () -> new IllegalArgumentException("음식점이 없습니다."));
        return foodRepository.findAllByRestaurantId(restaurantId);
    }

    @Transactional
    public void saveFood(Long restaurantId, List<FoodRequestDto> requestDto) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow( () -> new IllegalArgumentException("존재하지 않는 음식점 입니다."));
        for (FoodRequestDto f : requestDto) {
            if(!chkFoodName(restaurantId, f.getName()))
                throw new IllegalArgumentException("이미 등록된 음식입니다.");
            if(!chkFoodPrice(f.getPrice()))
                throw new IllegalArgumentException("음식 값은 100원 단위로만 등록 가능합니다");
            else {
                Food food = new Food(restaurantId, f.getName(), f.getPrice());
                foodRepository.save(food);
            }
        }
    }

    private boolean chkFoodName(Long restaurantId, String name) {
        List<Food> foodList = foodRepository.findByRestaurantIdAndName(restaurantId, name);
        if(foodList.size() != 0) {
            return false;
        } else return true;
    }

    private boolean chkFoodPrice(int price) {
        if (price % 100 != 0) {
            return false;
        }
        else return true;
    }
}
