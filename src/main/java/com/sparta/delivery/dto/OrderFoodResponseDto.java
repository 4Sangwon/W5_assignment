package com.sparta.delivery.dto;

import com.sparta.delivery.domain.OrderMenu;
import lombok.Getter;

@Getter
public class OrderFoodResponseDto {
    private String name;
    private int quantity;
    private int price;

    public OrderFoodResponseDto(OrderMenu orderMenu) {
        this.name = orderMenu.getMenuName();
        this.quantity = orderMenu.getQuantity();
        this.price = orderMenu.getPrice();
    }
}
