package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class OrderItem extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    // 하나의 상품은 여러 주문 상품으로 들어갈 수 있음 .
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    // 한번의 주문 <-> 여러개 상품
    // fetch lazy = 지연 로딩 -> 해당 엔티티만 조회 ( 연관되어있는 다른 엔티티들은 조회 X ,하려면 즉시로딩 )
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    // 가격
    private int orderPrice;

    // 수량
    private int count;

    // 주문
    public static OrderItem createOrderItem( Item item , int count ){

        OrderItem orderItem = new OrderItem();

        orderItem.setItem(item);
        orderItem.setCount(count);
        orderItem.setOrderPrice(item.getPrice());

        item.removeStock(count);
        return orderItem;

    }

    public int getTotalPrice(){
        return orderPrice*count;
    }
}



















