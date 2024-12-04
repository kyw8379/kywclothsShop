package com.example.kywclothsshop.repository;

import com.example.kywclothsshop.entity.ItemImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemImgRepository extends JpaRepository<ItemImg , Long> {

   public Optional<ItemImg> findById (Long ino);

    public ItemImg findByIdAndRepimgYn (Long ino , String val);



}
