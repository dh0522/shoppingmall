package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class ItemController {
    @GetMapping("/admin/item/new")
    public String itemForm(){
        return "/item/itemForm";
    }
}
