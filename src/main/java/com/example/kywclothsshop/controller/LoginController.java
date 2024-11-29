package com.example.kywclothsshop.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    @GetMapping("/searchid")
    public String searchidGet(){
        return "login/searchid";
    }
    // 포스트 서치아이디 만들기
   @GetMapping("/searchpw")
    public String searchpwGet(){
        return "/login/searchpw";
   }
    // 포스트 서치비번 만들기

}
