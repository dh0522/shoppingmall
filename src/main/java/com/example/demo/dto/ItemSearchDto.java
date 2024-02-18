package com.example.demo.dto;


import com.example.demo.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemSearchDto {

    /*
        현재 시간과 상품 등록일을 비교해서 상품데이터를 조회한다.
        all : 상품 등록일 전체
        1d: 최근 하루동안 등록된 상품
        1w: 최근 일주일동안 등록된 상품
        1m: 최근 한달동안 등록된 상품
        6m: 최근 6개월동안 등록된 상품
     */
    private String searchDateType;

    // 상품의 판매상태를 기준으로 상품데이터를 조회
    private ItemSellStatus searchSellStatus;

    // 상품을 조회할 때 어떤유형으로 조회할지 선택한다. -> itemNm , createdBy
    private String searchBy;

    // 조회할 검색어 저장할 변수
    private String searchQuery;
}
