package com.example.demo.dto;

import com.example.demo.entity.ItemImg;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter @Setter
public class ItemImgDto { // 상품 등록 및 수정에서 사용할 DTO

    private Long id;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    private String repImgYn;

    private static ModelMapper modelMapper = new ModelMapper();

    // ItemImg 객체를 파라미터로 받아서 ItemImg 의 자료형과 멤버면수의 이름이 같을 때 ItemImgDto 로 값을 복사해서 반환한다.
    public static ItemImgDto of(ItemImg itemImg){
        return modelMapper.map(itemImg,ItemImgDto.class);
    }
}
