package com.example.kywclothsshop.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDTO {

    private Long uno;  //유저번호 pk값

    private String uname;   //유저이름

    private String password;  //패스워드

    private LocalDate birthDate;  //생년월일

    private String phoneNumber;  //핸드폰번호

    private String address;  //주소

    private String email; //이메일

    private String bankAccount; //계좌번호



}
