package com.example.demo.exception;

public class OutOfStockException extends RuntimeException {

    public OutOfStockException(String message){
        super(message); // 부모 클래스 생성자 만들기
    }
}
