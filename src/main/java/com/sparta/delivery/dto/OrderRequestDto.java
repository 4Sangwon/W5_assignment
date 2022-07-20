package com.sparta.delivery.dto;

import com.sparta.delivery.domain.OrderMenu;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class OrderRequestDto {
    @NotNull
    private Long restaurantId;

    @NotNull
    @Range(min = 1, max = 100, message = "허용 주문 수량은 1개 ~ 100개 입니다.")
    private List<OrderMenu> foods;
}
