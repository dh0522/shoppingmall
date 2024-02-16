package com.example.demo.entity;

import com.example.demo.constant.ItemSellStatus;
import com.example.demo.dto.ItemFormDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "item")
@Getter @Setter
@ToString
public class Item extends BaseEntity {

    // 상품 코드
    @Id
    @Column(name="item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //상품명
    @Column(nullable = false, length = 50)
    private String itemNm;

    //가격
    @Column(name="price", nullable = false)
    private int price;

    // 재고
    @Column(nullable = false)
    private int stockNumber;

    // 상품 상세설명
    @Lob
    @Column(nullable = false)
    private String itemDetail;

    // 상품 판매 상태 - sell , soldout
    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;

    public void updateItem(ItemFormDto itemFormDto){
        this.itemNm = itemFormDto.getItemNm();
        this.price = itemFormDto.getPrice();
        this.stockNumber = itemFormDto.getStockNumber();
        this.itemDetail = itemFormDto.getItemDetail();
        this.itemSellStatus = itemFormDto.getItemSellStatus();
    }
}
