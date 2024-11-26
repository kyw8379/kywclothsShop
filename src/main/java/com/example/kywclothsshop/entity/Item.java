package com.example.kywclothsshop.entity;

import com.example.kywclothsshop.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "item")
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ino; //pk값 아이템번호

    @Column(nullable = false)
    private String iname; // 이름

    @Column(nullable = false)
    private String category; // 카테고리

    @Column(nullable = false)
    private int price; // 가격

    @Column(nullable = false)
    private int weight; // 몸무게

    @Column(nullable = false)
    private int height; // 키

    @Column(nullable = false)
    private int stockNumber; // 재고 수량

    @Column(length = 500)
    private String itemDetail; // 상세 설명

    @Column(nullable = false)
    private String itemSellStatus; // 판매 상태 (판매 중, 품절)

}
