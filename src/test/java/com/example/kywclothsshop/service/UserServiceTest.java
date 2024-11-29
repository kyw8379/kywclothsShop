package com.example.kywclothsshop.service;

import com.example.kywclothsshop.dto.UserDTO;
import com.example.kywclothsshop.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testSaveUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUname("김영우");
        userDTO.setPassword("1234");
        userDTO.setEmail("kyw8379@gmail.com");
        userDTO.setPhoneNumber("010-5838-8379");
        userDTO.setAddress("경기도");
        userDTO.setBirthDate(LocalDate.of(2001, 8, 13));
        userDTO.setBankAccount("3333137151086");

        User user = userService.saveUser(userDTO);

        assertNotNull(user.getUno());
        assertEquals("김영우", user.getUname());
    }
}
