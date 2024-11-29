package com.example.kywclothsshop.entity;

import com.example.kywclothsshop.constant.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "user")
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @Column(name = "uno")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uno;  //유저번호 pk값


    @NotNull
    @Column(nullable = false)
    private String uname;   //유저이름

    @NotNull
    @Column(nullable = false)
    private String password;  //패스워드

    @NotNull
    @Column(nullable = false)
    private LocalDate birthDate;  //생년월일
    @NotNull
    @Column(nullable = false)
    private String phoneNumber;  //핸드폰번호

    @NotNull
    @Column(nullable = false)
    private String address;  //주소

    @NotNull
    @Email
    @Column(nullable = false)
    private String email; //이메일

    @NotNull
    @Column(nullable = false)
    private String bankAccount; //계좌번호


    @Enumerated(EnumType.STRING)
    private Role role;
}
