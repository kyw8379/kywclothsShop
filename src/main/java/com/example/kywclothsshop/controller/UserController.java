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


    @GetMapping("/login")
    public String loginGet(){

        return "user/login" ;
    }

    @PostMapping("/login")
    public String loginPost(@Valid UserDTO userDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.info(bindingResult.getAllErrors());
            return "user/login"; // 유효성 검사 실패 시 로그인 페이지로 돌아간다.
        }

        // 로그인 추가
        try {

            boolean loginSuccess = userService.login(userDTO);

            if (!loginSuccess) {
                model.addAttribute("msg", "Invalid username or password");
                return "user/login"; // 로그인 실패 시 에러 메시지와 함께 로그인 페이지로 돌아감
            }

        } catch (Exception e) {
            model.addAttribute("msg", "Login failed: " + e.getMessage());
            return "user/login"; // 예외 발생 시 에러 메시지와 함께 로그인 페이지로 돌아감
        }

        return "redirect:/user/main"; // 로그인 성공 시 main 페이지로 리다이렉트
    }){

        return "user/login" ;
    }

    @GetMapping("/main")
    public String mainGet(){
        return "user/main";
    }


}
