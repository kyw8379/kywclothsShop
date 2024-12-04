package com.example.kywclothsshop.service;

import com.example.kywclothsshop.dto.ItemDTO;
import com.example.kywclothsshop.entity.Item;
import com.example.kywclothsshop.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class ItemService {

private final ItemRepository itemRepository;
private final ModelMapper modelMapper;

public Long saveItem(ItemDTO itemDTO , List<MultipartFile> multipartFiles) throws IOException {
    Item item = modelMapper.map(itemDTO , Item.class);

    item =itemRepository.save(item);

    //이미지 추가 등록 예정 itemimgservice;~

    return item.getIno();
}
    public ItemDTO read(Long ino, String email){


        Item item =
                itemRepository.findById(ino).orElseThrow(EntityNotFoundException::new);

        ItemDTO itemDTO = modelMapper.map(item, ItemDTO.class)
                .setItemImgDTOList(item.getItemImgList());



        return itemDTO;
    }




}
