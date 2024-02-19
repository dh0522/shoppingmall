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
public class Order extends BaseEntity {
    // 한명의 회원 <-> 여러개의 주문. 따라서 주문 입장에서 다대일
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // 주문 날짜
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    /*
         mapped by : 연관관계 주인 설정 : orderitem( 외래키가 order item 테이블에 있으니까 )
         cascade : 부모엔티티의 영속성 상태변화를 자식 엔티티에 모두 전이
         orphanRemoval : 고아 객체 ( 부모 엔티티와 연관관계가 끊어진 자식 엔티티 )

     */
    @OneToMany(mappedBy = "order" , cascade = CascadeType.ALL
                            , orphanRemoval = true , fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();

    // orderItem
    public void addOrderItem( OrderItem orderItem ){
        orderItems.add(orderItem);
        orderItem.setOrder(this);

    }

    public static Order createOrder( Member member , List<OrderItem> orderItemList ){

        Order order = new Order();

        order.setMember(member);
        for(OrderItem orderItem : orderItemList ){
            order.addOrderItem(orderItem);
        }

        order.setOrderStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;

    }

    public int getTotalPrice(){
        int totalPrice = 0;

        for(OrderItem orderItem : orderItems ){
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }
}

















