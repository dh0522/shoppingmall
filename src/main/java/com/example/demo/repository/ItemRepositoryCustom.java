package com.example.demo.repository;

import com.example.demo.dto.ItemSearchDto;
import com.example.demo.dto.MainItemDto;
import com.example.demo.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {

    // 메인 페이지에 보여줄 상품 리스트를 가져오는 메소드
    Page<Item> getAdmininItemPage(ItemSearchDto itemSearchDto , Pageable pageable);

    Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto , Pageable pageable);
}
