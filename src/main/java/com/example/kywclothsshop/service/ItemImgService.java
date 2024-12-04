package com.example.kywclothsshop.service;

import com.example.kywclothsshop.entity.Item;
import com.example.kywclothsshop.entity.ItemImg;
import com.example.kywclothsshop.repository.ItemImgRepository;
import com.example.kywclothsshop.repository.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
@Log4j2
public class ItemImgService {
    private final ItemRepository itemRepository;
    private final ItemImgRepository itemImgRepository;
    private final FileService fileService;

    public void saveImg(Long ino, List<MultipartFile> multipartFile) throws IOException {
        log.info(ino);
        if (multipartFile != null) {
            for (MultipartFile img : multipartFile) {
                if (!img.isEmpty()) {
                    log.info("아이템이미지서비스로 들어온 이미지" + img.getOriginalFilename());
                    //물리적인 저장
                    String savedFileName =      //uuid가 포함된 물리적인 파일이름
                            fileService.uploadFile(img);


                    // db저장
                    //엔티티를 가져왔다면 중복코드를 사용할 필요가 없어진다.해볼것
                    Item item =
                            itemRepository.findById(ino).get();

                    String imgUrl = "/images/item/" + savedFileName;

                    ItemImg itemImg = new ItemImg();
                    itemImg.setItem(item);      //본문 // 이미지가 달릴 아이템
                    itemImg.setImgName(savedFileName);       //uuid포함 저장될 이름
                    itemImg.setImgUrl(imgUrl);        //경로
                    itemImg.setOriImgName(img.getOriginalFilename());    // 원래이름
                    //대표이미지 여부 확인
                    if (multipartFile.indexOf(img) == 0) {
                        itemImg.setRepimgYn("Y");      //대표이미지
                    } else {
                        itemImg.setRepimgYn("N");
                    }
                    itemImgRepository.save(itemImg);

                }
            }
        }
    }
}