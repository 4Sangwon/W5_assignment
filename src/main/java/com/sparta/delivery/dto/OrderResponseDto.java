package com.sparta.delivery.dto;

import com.sparta.delivery.domain.Orders;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OrderResponseDto {
    private String restaurantName;
    private List<OrderFoodResponseDto> orderFoods;
    private int deliveryFee;
    private int totalPrice;

    public OrderResponseDto(Orders orders, List<OrderFoodResponseDto> orderFoods) {
        this.restaurantName  = orders.getRestaurant().getRestaurantName();
        this.orderFoods = orderFoods;
        this.deliveryFee = orders.getRestaurant().getDeliveryFee();
        this.totalPrice = orders.getTotalPrice();
    }
}
