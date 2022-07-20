package com.sparta.delivery.service;

import com.sparta.delivery.domain.Food;
import com.sparta.delivery.domain.OrderMenu;
import com.sparta.delivery.domain.Orders;
import com.sparta.delivery.domain.Restaurant;
import com.sparta.delivery.dto.OrderFoodResponseDto;
import com.sparta.delivery.dto.OrderRequestDto;
import com.sparta.delivery.dto.OrderResponseDto;
import com.sparta.delivery.repository.FoodRepository;
import com.sparta.delivery.repository.OrderMenuRepository;
import com.sparta.delivery.repository.OrderRepository;
import com.sparta.delivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final OrderMenuRepository orderMenuRepository;

    @Transactional
    public OrderResponseDto registerOrder(OrderRequestDto requestDto) {
        Restaurant restaurant = restaurantRepository.findById(requestDto.getRestaurantId())
                .orElseThrow(() -> new IllegalArgumentException("해당 음식점은 존재하지 않습니다."));
        // TODO : 음식점에 있는 메뉴인지 검사.
        List<OrderFoodResponseDto> orderFoodResponseDtoList = new ArrayList<>();
        List<OrderMenu> menuList = new ArrayList<>();
        int sum = 0;
        int i = 0;
        for (OrderMenu om : requestDto.getFoods()) {
            Food food = foodRepository.findById(om.getId())
                    .orElseThrow(() -> new IllegalArgumentException("해당 음식은 존재하지 않습니다."));
            if(om.getQuantity() > 100 || om.getQuantity() < 1)
                throw new IllegalArgumentException("허용 수량은 1개 ~ 100개 입니다.");
            OrderMenu orderMenu = new OrderMenu(food.getName(), om.getQuantity(), food.getPrice() * om.getQuantity());
            orderMenuRepository.save(orderMenu);
            OrderFoodResponseDto orderFoodResponseDto = new OrderFoodResponseDto(orderMenu);
            orderFoodResponseDtoList.add(orderFoodResponseDto);

            sum += orderMenu.getPrice();
            menuList.add(orderMenu);
        }

        if (restaurant.getMinOrderPrice() > sum) {
            throw new IllegalArgumentException("주문 금액이 최소 주문 금액보다 작습니다");
        }

        sum += restaurant.getDeliveryFee();
        Orders orders = new Orders(restaurant, menuList, sum);
        orderRepository.save(orders);

        return new OrderResponseDto(orders, orderFoodResponseDtoList);
    }

    @Transactional
    public List<OrderResponseDto> getOrders() {
        List<OrderResponseDto> orderResponseDtoList = new ArrayList<>();
        List<Orders> ordersList = orderRepository.findAll();

        for(Orders order : ordersList) {
            List<OrderMenu> orderMenuList = orderMenuRepository.findAll();
            List<OrderFoodResponseDto> orderFoodResponseDtoList = new ArrayList<>();
            for(OrderMenu orderMenu : orderMenuList) {
                OrderFoodResponseDto responseDto = new OrderFoodResponseDto(orderMenu);
                orderFoodResponseDtoList.add(responseDto);
            }

            OrderResponseDto orderResponseDto = new OrderResponseDto(order, orderFoodResponseDtoList);
            orderResponseDtoList.add(orderResponseDto);
        }

        return orderResponseDtoList;
    }
}
