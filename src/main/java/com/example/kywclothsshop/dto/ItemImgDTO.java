package com.example.kywclothsshop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ItemImgDTO {

    private Long id;

    private String imgNm;  //이미지 파일명

    private String oriImgNm; //원본 이미지명

    private String imgUrl; //이미지 조회 경로

    private String repimgYn; //대표이미지 여부

    private ItemDTO itemDTO;

}
