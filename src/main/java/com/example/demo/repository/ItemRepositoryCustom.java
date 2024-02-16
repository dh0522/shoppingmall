package com.example.demo.repository;

import com.example.demo.dto.ItemSearchDto;
import com.example.demo.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {
    Page<Item> getAdmininItemPage(ItemSearchDto itemSearchDto , Pageable pageable);
}
