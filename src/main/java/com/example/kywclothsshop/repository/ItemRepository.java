package com.example.kywclothsshop.repository;

import com.example.kywclothsshop.entity.Item;
import com.example.kywclothsshop.repository.search.ItemsearchRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item , Long>, ItemsearchRepository {
public List<Item> findByInam(String iname);

public Item findByInoAndCreateBy (Long ino , String email);

public List<Item> findByCreateBy (String email , Pageable pageable);

@Query("select i from Item i where i.iname = :iname")
    public List<Item> selectWhereIname(String iname);


}

