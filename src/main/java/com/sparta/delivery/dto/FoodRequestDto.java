package com.sparta.delivery.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class FoodRequestDto {

    @NotBlank
    private String name;

    @NotNull
    @Range(min = 100, max = 1000000, message = "허용 음식 가격은 100원 ~ 1,000,000원 입니다.")
    private int price;

//    @Valid private List<Foods> foods;
//
//    @Getter
//    public class Foods {
//        @NotBlank
//        private String foodName;
//
//        @NotNull
//        @Range(min = 100, max = 1000000, message = "허용 음식 가격은 100원 ~ 1,000,000원 입니다.")
//        private int foodPrice;
//    }
}
