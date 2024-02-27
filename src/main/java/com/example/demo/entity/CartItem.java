package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "cart_item")
public class CartItem extends BaseEntity {
    // CARTITEM 입장
    // 장바구니 하나 <-> 물건 여러개 : 다대일
    //

    @Id @GeneratedValue
    @Column(name = "cart_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int count;

    public static CartItem createCartItem( Cart cart ,Item item , int count){

        CartItem cartItem = new CartItem();
        cartItem.setItem(item);
        cartItem.setCart(cart);
        cartItem.setCount(count);
        return cartItem;
    }


    public void addCount( int count ){
        this.count += count;
    }
}








