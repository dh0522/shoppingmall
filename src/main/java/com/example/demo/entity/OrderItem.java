package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    // 하나의 상품은 여러 주문 상품으로 들어갈 수 있음 .
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    // 한번의 주문 <-> 여러개 상품
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    // 가격
    private int orderPrice;

    // 수량
    private int count;

    private LocalDateTime regTime;

    private LocalDateTime updateTime;
}
