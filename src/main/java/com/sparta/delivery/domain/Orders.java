package com.sparta.delivery.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
public class Orders {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurant restaurant;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "orders_id")
    private List<OrderMenu> menuList;

    @Column(nullable = false)
    private int totalPrice;

    public Orders(Restaurant restaurant, List<OrderMenu> menuList, int totalPrice) {
        this.restaurant = restaurant;
        this.menuList = menuList;
        this.totalPrice = totalPrice;
    }
}
