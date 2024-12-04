package com.example.kywclothsshop.repository;

import com.example.kywclothsshop.entity.Item;
import com.example.kywclothsshop.repository.search.ItemsearchRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item , Long>, ItemsearchRepository {
public List<Item> findByIname(String iname);

public Item findByInoAndCreateBy (Long ino , String email);

public List<Item> findByCreateBy (String email , Pageable pageable);

@Query("select i from Item i where i.iname = :iname")
    public List<Item> selectWhereIname(String iname);

public List<Item> findByInameContaining (String iname);

@Query("select i from Item i where i.iname like concat('%', :iname , '%') ")
public List<Item> selectWinameLike(String iname);

    public List<Item> findByItemDetailContaining (String itemDetail);

    public List<Item> findByPriceLessThan (Integer price);
    public List<Item> findByPriceGreaterThan (Integer price);
    public List<Item> findByPriceGreaterThanOrderByPriceAsc (Integer price);

    public List<Item> findByPriceGreaterThanEqual(Integer price, Pageable pageable);

    //정렬까지 추가
    List<Item> findByPriceLessThanOrderByPriceDesc  (Integer price);

    @Query(value = "select * from Item i  where i.ino = :iname",nativeQuery = true)
    List<Item> nativeQuerySelectwhereNamelike(String iname, Pageable pageable);


}

