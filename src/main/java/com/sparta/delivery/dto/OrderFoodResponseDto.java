package com.sparta.delivery.dto;

import com.sparta.delivery.domain.OrderMenu;
import lombok.Getter;

@Getter
public class OrderFoodResponseDto {
    private String foodName;
    private int quantity;
    private int foodPrice;

    public OrderFoodResponseDto(OrderMenu orderMenu) {
        this.foodName = orderMenu.getMenuName();
        this.quantity = orderMenu.getQuantity();
        this.foodPrice = orderMenu.getPrice();
    }
}
