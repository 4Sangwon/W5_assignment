package com.sparta.delivery.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Getter
public class RestaurantRequestDto {

    @NotNull
    private String restaurantName;

    @NotNull
    @Range(min = 1000, max = 100000, message = "허용 최소 주문 금액은 1,000원 ~ 100,000원 입니다.")
    private int minOrderPrice;

    @NotNull
    @Range(min = 0, max = 10000, message = "허용 배달비는 0원 ~ 10,000원 입니다.")
    private int deliveryFee;
}
