package com.sparta.delivery.service;

import com.sparta.delivery.domain.Restaurant;
import com.sparta.delivery.dto.RestaurantRequestDto;
import com.sparta.delivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public Restaurant saveRestaurant(RestaurantRequestDto requestDto) {
        Restaurant restaurant = new Restaurant(requestDto);
        if(!chkMinOrderPrice(requestDto.getMinOrderPrice())){
            throw new IllegalArgumentException("최소 주문 금액은 100원 단위로만 입력 가능합니다.");
        }
        if(!chkDeliveryFee(requestDto.getDeliveryFee())){
            throw new IllegalArgumentException("배달비는 500원 단위로만 입력 가능합니다.");
        }
        restaurantRepository.save(restaurant);

        return restaurant;
    }

    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }

    public boolean chkMinOrderPrice(int minOrderPrice) {
        //최소 주문 금액은 100원 단위로만 입력가능
        if(minOrderPrice % 100 != 0) return false;
        else return true;
    }

    public boolean chkDeliveryFee(int deliveryFee) {
        //배달비는 500원 단위로만 입력 가능
        if(deliveryFee % 500 != 0) return false;
        else return true;
    }
}
