package com.example.kywclothsshop.repository;

import com.example.kywclothsshop.entity.Item;
import com.example.kywclothsshop.repository.search.ItemsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item , Long>, ItemsearchRepository {





}
