package com.example.demo.controller;

import com.example.demo.dto.OrderDto;
import com.example.demo.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /*
    스프링에서 비동기 처리를 할 때 @ResponseBody , @RequestBody 사용한다.
        - @ResponseBody : http 요청의 본문 Body에 담긴 내용을 자바 객체로 전달한다.
        - @RequestBody : 자바 객체를 Http 요청의 body로 전달한다.
     */

    @PostMapping(value = "/order")
    public @ResponseBody ResponseEntity order (@RequestBody @Valid OrderDto orderDto,
                                               BindingResult bindingResult , Principal principal ){


        // 주문 정보를 받는 orderDto 객체에 데이터 바인딩 시 에러가 있는지
        if(bindingResult.hasErrors()){

            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

            for( FieldError fieldError : fieldErrors ){
                sb.append(fieldError.getDefaultMessage());
            }

            // 에러 정보를 ResponseEntity 객체에 담아서 반환한다.
            return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        String email = principal.getName();
        Long orderId;

        try{
            orderId = orderService.order(orderDto , email);
        }catch ( Exception e ){
            return new ResponseEntity<String>( e.getMessage() , HttpStatus.BAD_REQUEST );
        }

        return new ResponseEntity<Long>(orderId , HttpStatus.OK );
    }
}

