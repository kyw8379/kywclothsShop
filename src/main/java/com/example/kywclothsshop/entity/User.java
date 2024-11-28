package com.example.kywclothsshop.entity;

import com.example.kywclothsshop.constant.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uno;  //유저번호 pk값

    @Column(nullable = false)
    private String uname;   //유저이름

    @Column(nullable = false)
    private String password;  //패스워드

    @Column(nullable = false)
    private LocalDate birthDate;  //생년월일

    @Column(nullable = false)
    private String phoneNumber;  //핸드폰번호

    @Column(nullable = false)
    private String address;  //주소

    @Email
    @Column(nullable = false)
    private String email; //이메일

    @Column(nullable = false)
    private String bankAccount; //계좌번호

    @Enumerated(EnumType.STRING)
    private Role role;
}
