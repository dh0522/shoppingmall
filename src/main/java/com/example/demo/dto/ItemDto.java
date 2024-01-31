package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class ItemDto {

    private Long id;
    private String name;
    private int price;
    private String itemDetail;
    private String sellStatCd;
    private LocalDate regTime;
    private LocalDate updateTime;

}
