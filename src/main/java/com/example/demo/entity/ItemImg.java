package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "item_img")
@Getter @Setter

public class ItemImg extends BaseEntity {
    @Id
    @Column(name = "item_img_id")
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    // 이미지 파일명
    private String imgName;

    // 원본 이미지 파일명
    private String oriImgName;

    // 이미지 조회 경로
    private String imgUrl;

    // 대표 이미지 여부 - Y인 경우 메인페이지에서 상품을 보여줄 때 사용
    private String repimgYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public void updateItemImg(String oriImgName, String imgName , String imgUrl){

        this.oriImgName = oriImgName;
        this.imgName = imgName;
        this.imgUrl = imgUrl;
    }


}
