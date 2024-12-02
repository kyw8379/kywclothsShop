package com.example.kywclothsshop.controller;

import com.example.kywclothsshop.dto.UserDTO;
import com.example.kywclothsshop.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public String registerGet(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "user/register";
    }


    @PostMapping("/register")
    public String registerPost(@Valid UserDTO userDTO, BindingResult bindingResult, Model model) {
        log.info("저장의 post로 들어온 userDTO : " + userDTO);

        if (bindingResult.hasErrors()) {
            log.info(bindingResult.getAllErrors());
            return "user/register";
        }

        try {
            userService.saveUser(userDTO);


        } catch (IllegalStateException e) {
            model.addAttribute("msg", e.getMessage());
            return "user/register"; // 에러 메시지와 함께 회원가입 페이지로 반환
        }
        return "redirect:/user/login"; // 회원가입 성공 시 로그인 페이지로 리다이렉트
    }


    // 로그인 페이지로 이동
    @GetMapping("/login")
    public String loginGet() {
        return "user/login";
    }

    @GetMapping("/main")
    public String mainGet(){
        return "user/main";
    }

    }
