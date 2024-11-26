package com.example.kywclothsshop.dto;

import jakarta.persistence.Column;

public class ItemDTO {
    private Long ino; //pk값 아이템번호

    private String iname; // 이름

    private String category; // 카테고리

    private int price; // 가격

    private int weight; // 몸무게

    private int height; // 키

    private int stockNumber; // 재고 수량

    private String itemDetail; // 상세 설명

    private String itemSellStatus; // 판매 상태 (판매 중, 품절)
}
