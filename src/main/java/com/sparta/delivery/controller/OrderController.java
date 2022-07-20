package com.sparta.delivery.controller;

import com.sparta.delivery.dto.OrderRequestDto;
import com.sparta.delivery.dto.OrderResponseDto;
import com.sparta.delivery.service.OrderService;
import com.sparta.delivery.validator.CustomValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrderController {
    private final OrderService orderService;
    private final CustomValidator customValidator;

    @GetMapping("/orders")
    public List<OrderResponseDto> getOrders() {
        return orderService.getOrders();
    }

    @PostMapping("/order/request")
    public OrderResponseDto registerOrder(@RequestBody OrderRequestDto requestDto, Errors errors) {
        customValidator.validate(requestDto.getFoods(), errors);
        if(errors.hasErrors()) {
            throw new IllegalArgumentException("dd");
        }
        return orderService.registerOrder(requestDto);
    }
}
