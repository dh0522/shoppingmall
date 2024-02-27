package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name ="cart")
@Getter @Setter
@ToString
public class Cart extends BaseEntity {
    // 회원 한 명 <-> 장바구니 하나 : 일대일
    // 장바구니 하나 <-> 상품 여러개 : 일대다

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cart_id")
    private Long id;

    // 엔티티를 조회할 때 해당 엔티티와 매핑된 엔티티도 한 번에 조회 -> 즉시 로딩 , 해당 엔티티만 조회 -> 지연로딩 lazy
    //회원 엔티티와 일대일로 매핑
    @OneToOne ( fetch = FetchType.LAZY )
    @JoinColumn(name = "member_id") // 매핑할 외래키 지정
    private Member member;


    public static Cart createCart(Member member){

        Cart cart = new Cart();
        cart.setMember(member);
        return cart;
    }
}
