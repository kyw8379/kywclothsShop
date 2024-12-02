package com.example.kywclothsshop.service;

import com.example.kywclothsshop.repository.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class ItemService {

private final ItemRepository itemRepository;
private final ModelMapper modelMapper;

}
