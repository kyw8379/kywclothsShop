package com.example.kywclothsshop.dto;

import com.example.kywclothsshop.entity.ItemImg;
import jakarta.persistence.Column;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    private List<ItemImgDTO> itemImgDTOList;


    private String createBy;
    private LocalDateTime regTime;
    private LocalDateTime updateTime;


    public ItemDTO setItemImgDTOList(List<ItemImg> itemImgList) {

        ModelMapper modelMapper = new ModelMapper();

        List<ItemImgDTO> itemImgDTOS =
                itemImgList.stream().map(
                        itemImg -> modelMapper.map(itemImg, ItemImgDTO.class)
                ).collect(Collectors.toList());

        this.itemImgDTOList = itemImgDTOS;

        return this;
    }


}
