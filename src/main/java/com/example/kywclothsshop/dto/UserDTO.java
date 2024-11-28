package com.example.kywclothsshop.dto;

import com.example.kywclothsshop.constant.Role;
import com.example.kywclothsshop.entity.User;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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


    public User dtoToEntity (UserDTO userDTO){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User user = new User();

        user.setUname(userDTO.getUname());
        user.setEmail(userDTO.getEmail());
        user.setAddress(userDTO.getAddress());
        user.setBankAccount(userDTO.getBankAccount());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setBirthDate(userDTO.getBirthDate());


        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(Role.ADMIN);

        return user;
    }
}
