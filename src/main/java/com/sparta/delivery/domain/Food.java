package com.sparta.delivery.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Food {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "food_id")
    private Long id;

    @Column(nullable = false)
    private Long restaurantId;

    @Column(nullable = false)
    private String foodName;

    @Column(nullable = false)
    private int foodPrice;

    public Food(Long restaurantId, String foodName, int foodPrice) {
        this.restaurantId = restaurantId;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
    }


}
