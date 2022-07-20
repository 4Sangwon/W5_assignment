package com.sparta.delivery.controller;

import com.sparta.delivery.domain.Restaurant;
import com.sparta.delivery.dto.RestaurantRequestDto;
import com.sparta.delivery.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class RestaurantController {
    private final RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurants() {
        return restaurantService.getRestaurants();
    }

    @PostMapping("/restaurant/register")
    public Restaurant registRestaurant(@RequestBody @Valid RestaurantRequestDto requestDto) {
        Restaurant restaurant = restaurantService.saveRestaurant(requestDto);
        return restaurant;
    }

//    @GetMapping("/restaurants")
//    public ResponseEntity getRestaurant(){
//        List<Restaurant> results = restaurantService.getRestaurants();
//        return new ResponseEntity(results, HttpStatus.OK);
//    }
//
//    @PostMapping("/restaurant/register")
//    public void register(@RequestBody @Valid RestaurantRequestDto requestDto){
//        Restaurant restaurant = restaurantService.saveRestaurant(requestDto);
//    }

}
