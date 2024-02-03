package com.example.demo.entity;

import com.example.demo.constant.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {
    // 한명의 회원 <-> 여러개의 주문. 따라서 주문 입장에서 다대일
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    // 주문 날짜
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order") // 연관관계 주인 설정 : orderitem( 외래키가 order item 테이블에 있으니까 )
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime regTime;

    private LocalDateTime updateTime;
}
