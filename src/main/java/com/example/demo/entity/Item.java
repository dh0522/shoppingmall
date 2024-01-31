package com.example.demo.entity;

import com.example.demo.constant.ItemSellStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
@Getter @Setter
@Table(name = "item")
public class Item {
    // 상품 코드

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //상품명
    private String name;

    //가격
    private int price;

    // 재고
    private int stockNumber;

    // 상품 상세설명
    private String itemDetail;

    // 상품 판매 상태 - sell , soldout
    private ItemSellStatus itemSellStatus;

    // 등록 시간
    private LocalDate regTime;

    // 수정 시간
    private LocalDate updateTime;
}
