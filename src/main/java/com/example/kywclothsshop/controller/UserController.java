package com.example.kywclothsshop.controller;

import com.example.kywclothsshop.dto.UserDTO;
import com.example.kywclothsshop.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.print.attribute.standard.PrinterURI;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    // 유저 회원가입
    @GetMapping("/register")
    public String registerGet(Model model){
        model.addAttribute("userDTO",new UserDTO());
        return "user/register";
    }

    @PostMapping("/register")
    public String registerPost(@Valid UserDTO userDTO , BindingResult bindingResult , Model model) {
        log.info("저장의 post로 들어온 userDTO : " + userDTO);
        if (bindingResult.hasErrors()) {
            //유효성검사에 이상이 있다면 다시 회원가입페이지로 보낼것이다.
            // 무엇을 가지고? 에러내용을 그건 어디있니? 자동으로 넘어간다
            // 단 return 으로 redirect 안됨 그건 RedirectAttributes 에 따로 담아야함
            // 에러는 무엇인가 로그
            log.info(bindingResult.getAllErrors());
            // 이 에러를 가져오는 getAllErrors의 내용을 리다이렉트로 보낼때 가져가면된다.

            return "user/register";

        }
        try {
            userService.saveUser(userDTO);
        } catch (IllegalStateException e) {
            model.addAttribute("msg", e.getMessage());
            return "user/register";
        }
        return null;

    }
    @GetMapping("/login")
    public String loginGet(){
        return null;
    }

}
