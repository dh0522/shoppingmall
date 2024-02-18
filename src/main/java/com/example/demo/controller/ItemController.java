package com.example.demo.controller;

import com.example.demo.dto.ItemFormDto;
import com.example.demo.dto.ItemSearchDto;
import com.example.demo.entity.Item;
import com.example.demo.service.ItemService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    @GetMapping(value = "/admin/item/new")
    public String itemForm(Model model){
        model.addAttribute("itemFormDto",new ItemFormDto());
        return "item/itemForm";
    }

    @PostMapping(value = "/admin/item/new")
    public String itemNew(@Valid ItemFormDto itemFormDto , BindingResult bindingResult
            , Model model , @RequestParam(name = "itemImgFile") List<MultipartFile> itemImgFileList ){

        if(bindingResult.hasErrors()){
            return "item/itemForm";
        }
        if(itemImgFileList.get(0).isEmpty() && itemFormDto.getId() == null ){
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값입니다.");
            return "item/itemForm";
        }
        try{
            itemService.saveItem(itemFormDto,itemImgFileList);
        }catch (Exception e) {
            model.addAttribute("errorMessage","상품 등록 중 에러가 ㅈㄷㄱㅈㄱㄷ 발생하였습니다.");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return "item/itemForm";
        }

        return "redirect:/";
    }

    @GetMapping(value = "/admin/item/{itemId}")
    public String itemDtl(@PathVariable(name = "itemId") Long itemId , Model model ){

        try{

            // 조회한 상품 데이터를 모델에 담아서 뷰로 전송한다.
            ItemFormDto itemFormDto = itemService.getItemDtl(itemId);
            model.addAttribute( "itemFormDto", itemFormDto );

        } catch (  EntityNotFoundException e ){
            // 상품 엔티티가 존재하지 않을 경우 에러메시지를 담아서 상품등록페이지로 이동한다.

            model.addAttribute("errorMessage", "존재하지 않는 상품입니다.");
            model.addAttribute("itemFormDto", new ItemFormDto() );

            return "item/itemForm";

        }
        return "item/itemForm";

    }



    @PostMapping(value = "/admin/item/{itemId}")
    public String itemUpdate(@Valid ItemFormDto itemFormDto , BindingResult bindingResult
            , @RequestParam(name = "itemImgFile") List<MultipartFile> itemImgFileList , Model model){

        if(bindingResult.hasErrors()){
            return "item/itemForm";
        }
        if(itemImgFileList.get(0).isEmpty() && itemFormDto.getId() == null ){

            model.addAttribute("errorMessage","첫번째 상품 이미지는 필수 입력 값 입니다.");
            return  "item/itemForm";

        }

        try{
            itemService.updateItem(itemFormDto, itemImgFileList);
        }catch( Exception e ){
            model.addAttribute("errorMessage","상품 수정 중 에러가 발생하였습니다. ");
            return "item/itemForm";
        }
        return "redirect:/";
    }



    @GetMapping(value = {"/admin/items", "/admin/items/{page}"})
    public String itemManage(ItemSearchDto itemSearchDto , @PathVariable(name = "page")Optional<Integer> page, Model model ){


        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,3 );
        Page<Item> items = itemService.getAdminItemPage(itemSearchDto, pageable);

        model.addAttribute("items",items);
        model.addAttribute("itemSearchDto",itemSearchDto);
        model.addAttribute("maxPage",5);

        return "item/itemMng";
    }
}
















