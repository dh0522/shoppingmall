package com.example.demo.repository;

import com.example.demo.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    // save , delete , count , findAll 지원해줌

    List<Item> findByName(String name);
    List<Item> findByNameOrItemDetail(String name, String itemDetail);
    List<Item> findByPriceLessThan(Integer price);
}
